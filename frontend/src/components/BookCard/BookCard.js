import React from "react";
import Rating from "@material-ui/lab/Rating";
import "./BookCard.css";
import AllInboxIcon from "@mui/icons-material/AllInbox";
import BookmarkIcon from "@mui/icons-material/Bookmark";
const BookCard = (props) => {
  const {
    title,
    coverImage,
    description,
    rating,
    copiesLeft,
    total,
    isbn,
    onBorrow,
  } = props;

  return (
    <div className="card">
      <BookmarkIcon className="bookmarkIcon" />
      <div className="titleHolder">
        <text className="card__title">{title}</text>
      </div>

      <div className="bookMiddle">
        <div className="middleLeft">
          <div>
            <img className="card__image" src={coverImage} alt={title} />
            <text className="isbnText">ISBN: {isbn}</text>
          </div>
        </div>
        <div className="middleRight">
          <div className="ratingHolder">
            <Rating
              name="rating"
              value={rating}
              max={5}
              precision={0.2}
              readOnly
            />
            <span>{rating.toFixed(1)}</span>
          </div>

          <div className="card__content">
            <p className="card__description">{description}</p>
          </div>
        </div>
      </div>

      <div className="bookBottom">
        <div className="bottomLeft">
          <AllInboxIcon />
          <text className="bookLeftText">
            {copiesLeft}/{total}
          </text>
        </div>
        <div>
          {copiesLeft > 0 && (
            <button
              className="card__button btn borrowButton"
              onClick={onBorrow}
            >
              Borrow
            </button>
          )}
          {copiesLeft == 0 && (
            <button className="card__button btn borrowButton disabledBorrow">
              No book
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default BookCard;
