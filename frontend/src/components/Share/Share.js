import React from "react";
import {useState, useEffect} from "react";
import {useHistory} from "react-router-dom";
import "./Share.css";
import {PermMedia, Label} from "@mui/icons-material";
import Button from "../Button/Button";

const Share = ({jobs}) => {
    const [showError, setShowError] = useState(false);
    const [isbn, setISBN] = useState("");
    const [name, setName] = useState("");
    const [format, setFormat] = useState("");
    const [author, setAuthor] = useState("");
    const [image, setImage] = useState("");
    const [totalCopies, setTotalCopies] = useState("");
    const [copiesLeft, setCopiesLeft] = useState("");
    const [category, setCategory] = useState('');
    const [description, setDescription] = useState("");
    const [rate, setRate] = useState("");
    const [imageInput, setImageInput] = useState(false);
    const [isAnnouncement, setIsAnnouncement] = useState(false);

    const [profileImage, setProfileImage] = useState("");

    const user = JSON.parse(sessionStorage.getItem("user"));
    const token = sessionStorage.getItem("token");
    const history = useHistory();

    const linkFormatter = (link) => {
        if (link.charAt(0) === ".") {
            link = link.substring(1);
        }

        return link;
    };

    const opts = {
        method: "GET",
        headers: {
            "Content-type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    };

    useEffect(() => {
        fetch(`http://localhost:8080/api/profile/${user.sub}`, opts)
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setProfileImage(linkFormatter(data.profilePicture));
                return data;
            })
            .catch((error) => {
                console.error("There's an error", error);
            });
    }, [user.sub]);

    const imageHandle = () => {
        setImageInput(!imageInput);
    }

    const announcementHandle = () => {
        setIsAnnouncement(!isAnnouncement);
    }

    const redirectTo = (path) => {
        history.push(path);
        window.scrollTo(0, 0);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if ((isbn === "") || (name === "") || (format === "") || (author === "") || (image === "") || (totalCopies === "") || (copiesLeft === "") || (category === "") || (rate === "") || (description === "")) {
            setShowError(true);
            return;
        }

        const opts = {
            method: "POST",
            headers: {
                "Content-type": "application/json",
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify({
                isbn: isbn,
                name: name,
                format: format,
                author: author,
                photoURL: image,
                totalCopies: totalCopies,
                copiesLeft: copiesLeft,
                category: category,
                description: description,
                rate: rate,
            }),
        };
        fetch("http://localhost:8080/api/book/addBook", opts)
            .then((res) => {
                console.log(res);
            })
            .then(() => {
                window.location.reload();
            })
            .catch((error) => {
                console.error("There's an error", error);
            });
    }


    return (
        <div className="share">
            <div className="shareWrapper">
                <form className="shareForm" onSubmit={handleSubmit}>
                    <div className="shareTop">
                        <img
                            className="shareProfileImg"
                            src={profileImage}
                            alt=""
                        />
                        <input
                            placeholder="Book Name"
                            className="shareInput"
                            onChange={(event) => setName(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="ISBN"
                            className="shareInput"
                            onChange={(event) => setISBN(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Book description (optional)"
                            className="shareInput"
                            onChange={(event) => setDescription(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Image URL"
                            className="shareInput"
                            onChange={(event) => setImage(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Author name"
                            className="shareInput"
                            onChange={(event) => setAuthor(event.target.value)}
                        />
                    </div>

                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Category"
                            className="shareInput"
                            onChange={(event) => setCategory(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Format"
                            className="shareInput"
                            onChange={(event) => setFormat(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Total Copies"
                            className="shareInput"
                            onChange={(event) => setTotalCopies(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Copies Left"
                            className="shareInput"
                            onChange={(event) => setCopiesLeft(event.target.value)}
                        />
                    </div>
                    <hr className="shareHr"/>
                    <div className="shareDesc">
                        <input
                            placeholder="Rate ?/5"
                            className="shareInput"
                            onChange={(event) => setRate(event.target.value)}
                        />
                    </div>
                    <div className="shareBottom">
                        <div className="shareOptions">
                            {!jobs && user && (user.roles[0] === "ROLE_ADMIN" || user.roles[0] === "ROLE_ACADEMICIAN") && isAnnouncement && (
                                <div className="shareOption" onClick={announcementHandle}>
                                    <Label color="primary" className="shareIcon"/>
                                    <span className="shareOptionText unselectable">Announcement</span>
                                </div>
                            )}
                        </div>
                        {showError && (
                            <h4 className="alert-text">Invalid Title or Description!</h4>
                        )}
                        <Button text="Add Book" type="submit" buttonType="btn shareButton unselectable"/>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Share;
