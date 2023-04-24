import React, { useEffect, useRef, useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Feed from "../../components/Feed/Feed";
import AlertSnackbar from "../../components/Snackbar/AlertSnackbar";
import "./Home.css";
import SearchPage from "../../components/SearchPage/SearchPage";

const Home = (props) => {
  const [followPing, setFollowPing] = useState(true);
  const [isSearched, setIsSearched] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortBy, setSortBy] = useState(null);
  const [searchBy, setSearchBy] = useState(["title"]);
  const [searchPage, setSearchPage] = useState(0);

  const searchBooks = (searchQuery, searchBy, sortBy) => {
    setIsSearched(!(searchQuery.length == 0));
    setSearchBy(searchBy);
    setSearchTerm(searchQuery);
    setSortBy(sortBy);
    console.log(searchQuery, searchBy, sortBy);
  };

  const user = JSON.parse(sessionStorage.getItem("user"));

  const ref = useRef(null);

  const scrollToInvisible = () => {
    const scrollHeight = window.innerHeight;
    const currentPosition = window.pageYOffset;
    const targetPosition = currentPosition + scrollHeight;
    const scroll = () => {
      const currentY = window.pageYOffset;
      if (currentY < targetPosition) {
        window.scrollTo(
          0,
          currentY + Math.min((targetPosition - currentY) / 10, 100)
        );
        window.requestAnimationFrame(scroll);
      } else {
        // Stop scrolling animation
        window.scrollTo(0, targetPosition);
      }
    };
    window.requestAnimationFrame(scroll);
  };

  return (
    <>
      <SearchPage
        searchBooks={searchBooks}
        scrollToInvisible={scrollToInvisible}
      />
      <div className="homeContainer">
        <Feed
          type="home"
          profilename={user.sub}
          isSearched={isSearched}
          keyword={props.keyword}
          followPing={followPing}
          setFollowPing={setFollowPing}
          searchTerm={searchTerm}
          searchPage={searchPage}
          searchBy={searchBy}
          sortBy={sortBy}
        />
      </div>
    </>
  );
};

export default Home;
