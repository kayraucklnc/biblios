import React from "react";
import "./PageSelection.css";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";

const PageSelection = ({ currentPage, totalPages, onPageChange }) => {
  const MAX_PAGE_NUMBERS = 5;
  const pageNumbers = [];

  // Add ellipses to indicate there are more pages
  if (totalPages > MAX_PAGE_NUMBERS) {
    const middleIndex = Math.floor(MAX_PAGE_NUMBERS / 2);
    const startPage =
      currentPage - middleIndex > 1 ? currentPage - middleIndex : 1;
    const endPage =
      currentPage + middleIndex <= totalPages
        ? currentPage + middleIndex
        : totalPages;

    if (startPage > 1) {
      pageNumbers.push(1);
      if (startPage > 2) {
        pageNumbers.push("...");
      }
    }

    for (let i = startPage; i <= endPage; i++) {
      pageNumbers.push(i);
    }

    if (endPage < totalPages) {
      if (endPage < totalPages - 1) {
        pageNumbers.push("...");
      }
      pageNumbers.push(totalPages);
    }
  } else {
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
    }
  }

  return (
    <div className="page-selection-container">
      <ul className="page-selection">
        <li
          className={`page-number prevent-select ${
            currentPage === 1 ? "page-number-disabled" : ""
          }`}
          onClick={() => onPageChange(currentPage - 1)}
        >
          <KeyboardArrowLeftIcon />
        </li>

        {pageNumbers.map((number, index) => (
          <li
            key={index}
            className={`page-number prevent-select ${
              number === currentPage ? "page-number-active" : ""
            } ${number === "..." ? "page-number-ellipsis" : ""}`}
            onClick={() => {
              if (typeof number === "number") {
                onPageChange(number);
              }
            }}
          >
            {number}
          </li>
        ))}

        <li
          className={`page-number ${
            currentPage === totalPages ? "page-number-disabled" : ""
          }`}
          onClick={() => onPageChange(currentPage + 1)}
        >
          <KeyboardArrowRightIcon />
        </li>
      </ul>
    </div>
  );
};

export default PageSelection;
