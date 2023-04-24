import "./SearchPage.css";
import React, { useState } from "react";

const SearchPage = ({ searchBooks, scrollToInvisible }) => {
  const [searchQuery, setSearchQuery] = useState("");
  const [searchBy, setSearchBy] = useState(["title"]);
  const [sortBy, setSortBy] = useState("");

  const handleSearchQueryChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchByChange = (event) => {
    const { name, checked } = event.target;

    if (checked) {
      setSearchBy((prevSearchBy) => [...prevSearchBy, name]);
    } else {
      setSearchBy((prevSearchBy) =>
        prevSearchBy.filter((field) => field !== name)
      );
    }
  };

  const handleSortByChange = (event) => {
    setSortBy(event.target.value);
  };

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    searchBooks(searchQuery, searchBy, sortBy);
    scrollToInvisible();
  };

  return (
    <form className="search-page" onSubmit={handleSearchSubmit}>
      <text htmlFor="search-query" className="sr-only">
        Biblios
      </text>
      <input
        type="text"
        id="search-query"
        placeholder="Search books"
        value={searchQuery}
        onChange={handleSearchQueryChange}
        className="search-box"
        autocomplete="off"
      />

      {/*<div className="search-by">*/}
      {/*  <span>Search By:</span>*/}
      {/*  <label>*/}
      {/*    <input*/}
      {/*      type="checkbox"*/}
      {/*      name="title"*/}
      {/*      checked={searchBy.includes("title")}*/}
      {/*      onChange={handleSearchByChange}*/}
      {/*    />*/}
      {/*    Title*/}
      {/*  </label>*/}
      {/*  <label>*/}
      {/*    <input*/}
      {/*      type="checkbox"*/}
      {/*      name="author"*/}
      {/*      checked={searchBy.includes("author")}*/}
      {/*      onChange={handleSearchByChange}*/}
      {/*    />*/}
      {/*    Author*/}
      {/*  </label>*/}
      {/*  <label>*/}
      {/*    <input*/}
      {/*      type="checkbox"*/}
      {/*      name="category"*/}
      {/*      checked={searchBy.includes("category")}*/}
      {/*      onChange={handleSearchByChange}*/}
      {/*    />*/}
      {/*    Category*/}
      {/*  </label>*/}
      {/*</div>*/}

      {/*<div className="sort-by">*/}
      {/*  <span>Sort By:</span>*/}
      {/*  <select value={sortBy} onChange={handleSortByChange}>*/}
      {/*    <option value="">None</option>*/}
      {/*    <option value="title">Title</option>*/}
      {/*    <option value="author">Author</option>*/}
      {/*    <option value="category">Category</option>*/}
      {/*  </select>*/}
      {/*</div>*/}

      <button type="submit" className="search-button">
        Search
      </button>
    </form>
  );
};

export default SearchPage;
