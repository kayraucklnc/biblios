import React, { useEffect } from "react";
import { useState } from "react";
import "./Feed.css";
import Share from "../Share/Share";
import Post from "../Post/Post";
import Loader from "../Loader/Loader";
import Announcement from "../Announcement/Announcement";
import JobApplicationForm from "../JobApplicationForm/JobApplicationForm";
import { useHistory } from "react-router-dom";
import { SentimentVeryDissatisfiedRounded } from "@mui/icons-material";
import ProfileCard from "../ProfileCard/ProfileCard";
import BookCard from "../BookCard/BookCard";
import PageSelection from "../PageSelection/PageSelection";

const Feed = ({
  type,
  profilename,
  isSearched,
  keyword,
  followPing,
  setFollowPing,
  searchTerm,
  searchPage,
  searchBy,
  sortBy,
  scrollToInvisible,
}) => {
  const [loading, setLoading] = useState(true);
  const [loadingBooks, setLoadingBooks] = useState(true);
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemCount, seItemCount] = useState(0);

  const [posts, setPosts] = useState([]);
  const [books, setBooks] = useState([]);
  const [announcements, setAnnouncements] = useState([]);
  const [users, setUsers] = useState([]);
  const [jobs, setJobs] = useState([]);
  const [isHome, setIsHome] = useState(type === "home" ? true : false);
  const [isProfile, setIsProfile] = useState(type === "profile" ? true : false);
  const [isAnnouncements, setIsAnnouncements] = useState(
    type === "announcements" ? true : false
  );
  const [isJobs, setIsJobs] = useState(type === "jobs" ? true : false);

  const user = JSON.parse(sessionStorage.getItem("user"));
  const token = sessionStorage.getItem("token");
  const history = useHistory();

  const opts = {
    method: "GET",
    headers: {
      "Content-type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };


  useEffect(() => {
    let urlEnds = user.roles[0] !== "ROLE_ADMIN" ? "/feed" : "";
    if (!searchTerm || searchTerm === "") {
      if (isHome) {
        fetch("http://localhost:8080/api/book/" + (currentPage - 1), opts)
          .then((res) => {
            return res.json();
          })
          .then((data) => {
            setBooks(data.content);
            setTotalPages(data.totalPages);
            console.log(data.content);
            return data;
          })
          .then(() => {
            setLoadingBooks(false);
          })
          .catch((error) => {
            console.error("There's an error", error);
          });
      } else if (isProfile) {
        fetch(`http://localhost:8080/api/user/books/${profilename}/${currentPage - 1}`, opts)
          .then((res) => {
            return res.json();
          })
          .then((data) => {
            setBooks(data.content);
            setTotalPages(data.totalPages);
            console.log(data.content);
            return data;
          })
          .then(() => {
            setLoadingBooks(false);
          })
          .catch((error) => {
            console.error("There's an error", error);
          });
      }
    }
  }, [profilename, searchTerm, currentPage]);

  useEffect(() => {
    if (searchTerm && searchTerm !== "" && isSearched) {
      setLoading(true);
      const opts = {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      };

      fetch(
        "http://localhost:8080/api/search/" +
          searchTerm +
          "/" +
          (currentPage - 1),
        opts
      )
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data.content)
          setBooks(data.content);
          setTotalPages(data.totalPages);
          seItemCount(data.totalElements);
          return data;
        })
        .then(() => {
          setLoadingBooks(false);
        })
        .catch((error) => {
          console.error("There's an error", error);
        });
    }
  }, [searchTerm, isSearched, currentPage]);

  useEffect(() => {
    setCurrentPage(1);
  }, [isSearched]);

  return (
    <div className="feed">
      <PageSelection
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={(pageClicked) => {
          setCurrentPage(pageClicked);
        }}
      />
      {!isSearched && (isHome || isProfile) && (
        <div className="feedWrapper">
          {loadingBooks && (
            <center>
              <Loader />
            </center>
          )}

          {!loadingBooks &&
            books.map((b) => (
              <BookCard
                key={b.isbn}
                title={b.name}
                coverImage={b.photoURL}
                description={b.description}
                rate={b.rate}
                copiesLeft={b.copiesLeft}
                totalCopies={b.totalCopies}
                isbn={b.isbn}
                author={b.author}
                format={b.format}
                category={b.category}
                hideBorrow={!isHome}
                series={b.series?.name}
              />
            ))}

          {!loadingBooks && books.length === 0 && (
            <div>
              <div className="notFound">
                <br></br>{" "}
                <SentimentVeryDissatisfiedRounded
                  style={{ fontSize: 500, alignSelf: "center" }}
                />
                <b> No Books Found! </b>
              </div>
            </div>
          )}
        </div>
      )}

      {isSearched && (
        <div className="feedWrapper">
          <div id="postResults">
            <div className="searchInfo">
              <b>{itemCount}</b> book result{itemCount !== 1 && "s"} found for "
              <b>{searchTerm}</b>":
            </div>
            {loadingBooks && (
              <center>
                <Loader />
              </center>
            )}
            {!loadingBooks &&
              books.map((b) => (
                <BookCard
                  key={b.isbn}
                  title={b.name}
                  coverImage={b.photoURL}
                  description={b.description}
                  rate={b.rate}
                  copiesLeft={b.copiesLeft}
                  totalCopies={b.totalCopies}
                  isbn={b.isbn}
                  author={b.author}
                  format={b.format}
                  category={b.category}
                  hideBorrow={!isHome}
                  series={b.series?.name}
                />
              ))}
          </div>
          <div>
            {books.length === 0 && (
              <div>
                <div className="notFound">
                  <br></br>{" "}
                  <SentimentVeryDissatisfiedRounded
                    style={{ fontSize: 500, alignSelf: "center" }}
                  />
                  <b> No Results Found! </b>
                </div>
              </div>
            )}
          </div>
        </div>
      )}
      <PageSelection
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={(pageClicked) => {
          setCurrentPage(pageClicked);
        }}
      />
    </div>
  );
};

export default Feed;
