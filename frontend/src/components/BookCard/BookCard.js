import React from "react";
import Rating from "@material-ui/lab/Rating";
import "./BookCard.css";
import AllInboxIcon from "@mui/icons-material/AllInbox";

import { Icon } from "@material-ui/core";
import {
  AccessibilityNewOutlined,
  BookOutlined,
  EmojiEmotionsOutlined,
  FlightTakeoffOutlined,
  GavelOutlined,
  MonetizationOnOutlined,
  SportsOutlined,
  FastfoodOutlined,
  FaceOutlined,
  ComputerOutlined,
  TvOutlined,
  HistoryOutlined,
  LocalHospitalOutlined,
  PeopleAltOutlined,
  CreateOutlined,
  NaturePeopleOutlined,
  AccountBoxOutlined,
  PublicOutlined,
  FavoriteOutlined,
  GroupOutlined,
  ChildFriendlyOutlined,
  MoodOutlined,
  SchoolOutlined,
  DirectionsCarOutlined,
  EcoOutlined,
} from "@material-ui/icons";

function getIconByText(text) {
  const normalizedText = text.toLowerCase();

  if (normalizedText.includes("mind") || normalizedText.includes("spirit")) {
    return <AccessibilityNewOutlined />;
  }

  if (normalizedText.includes("reference")) {
    return <BookOutlined />;
  }

  if (
    normalizedText.includes("personal") ||
    normalizedText.includes("development")
  ) {
    return <EmojiEmotionsOutlined />;
  }

  if (
    normalizedText.includes("travel") ||
    normalizedText.includes("holiday") ||
    normalizedText.includes("guides")
  ) {
    return <FlightTakeoffOutlined />;
  }

  if (normalizedText.includes("crime") || normalizedText.includes("thriller")) {
    return <GavelOutlined />;
  }

  if (
    normalizedText.includes("business") ||
    normalizedText.includes("finance") ||
    normalizedText.includes("law")
  ) {
    return <MonetizationOnOutlined />;
  }

  if (normalizedText.includes("sport")) {
    return <SportsOutlined />;
  }

  if (normalizedText.includes("food") || normalizedText.includes("drink")) {
    return <FastfoodOutlined />;
  }

  if (normalizedText.includes("religion")) {
    return <FaceOutlined />;
  }

  if (
    normalizedText.includes("teen") ||
    normalizedText.includes("young") ||
    normalizedText.includes("adult")
  ) {
    return <FaceOutlined />;
  }

  if (normalizedText.includes("computing")) {
    return <ComputerOutlined />;
  }

  if (
    normalizedText.includes("technology") ||
    normalizedText.includes("engineering")
  ) {
    return <TvOutlined />;
  }

  if (normalizedText.includes("entertainment")) {
    return <HistoryOutlined />;
  }

  if (
    normalizedText.includes("history") ||
    normalizedText.includes("archaeology")
  ) {
    return <HistoryOutlined />;
  }

  if (normalizedText.includes("medical")) {
    return <LocalHospitalOutlined />;
  }

  if (
    normalizedText.includes("graphic") ||
    normalizedText.includes("novels") ||
    normalizedText.includes("anime") ||
    normalizedText.includes("manga")
  ) {
    return <PeopleAltOutlined />;
  }

  if (normalizedText.includes("biography")) {
    return <CreateOutlined />;
  }

  if (normalizedText.includes("stationery")) {
    return <CreateOutlined />;
  }

  if (
    normalizedText.includes("science") ||
    normalizedText.includes("fiction") ||
    normalizedText.includes("fantasy") ||
    normalizedText.includes("horror")
  ) {
    return <PublicOutlined />;
  }

  if (normalizedText.includes("home") || normalizedText.includes("garden")) {
    return <NaturePeopleOutlined />;
  }

  if (
    normalizedText.includes("art") ||
    normalizedText.includes("photography")
  ) {
    return <AccountBoxOutlined />;
  }

  if (normalizedText.includes("geography")) {
    return <PublicOutlined />;
  }

  if (normalizedText.includes("romance")) {
    return <FavoriteOutlined />;
  }

  if (normalizedText.includes("poetry") || normalizedText.includes("drama")) {
    return <CreateOutlined />;
  }

  if (
    normalizedText.includes("society") ||
    normalizedText.includes("social") ||
    normalizedText.includes("sciences")
  ) {
    return <GroupOutlined />;
  }

  if (normalizedText.includes("children")) {
    return <ChildFriendlyOutlined />;
  }

  if (
    normalizedText.includes("dictionaries") ||
    normalizedText.includes("languages")
  ) {
    return <SchoolOutlined />;
  }

  if (normalizedText.includes("health")) {
    return <LocalHospitalOutlined />;
  }

  if (normalizedText.includes("humour")) {
    return <MoodOutlined />;
  }

  if (normalizedText.includes("crafts") || normalizedText.includes("hobbies")) {
    return <CreateOutlined />;
  }

  if (
    normalizedText.includes("teaching") ||
    normalizedText.includes("resources") ||
    normalizedText.includes("education")
  ) {
    return <SchoolOutlined />;
  }

  if (normalizedText.includes("transport")) {
    return <DirectionsCarOutlined />;
  }

  if (
    normalizedText.includes("natural") ||
    normalizedText.includes("history")
  ) {
    return <EcoOutlined />;
  }

  // Default to the book icon if no specific match was found
  return <BookOutlined />;
}

function BookCategory({ category }) {
  const icon = getIconByText(category);

  return (
    <div className="category">
      <Icon>{icon}</Icon>
      <span>{category}</span>
    </div>
  );
}

const BookCard = (props) => {
  const {
    title,
    coverImage,
    description,
    rate,
    copiesLeft,
    totalCopies,
    isbn,
    author,
    format,
    category,
    onBorrow,
    hideBorrow
  } = props;

  return (
    <div className="card">
      <BookCategory category={category} />
      <div className="titleHolder">
        <text className="card__title">{title}</text>
      </div>

      <div className="bookMiddle">
        <div className="middleLeft">
          <img className="card__image" src={coverImage} alt={title} />
          <text className="authorText">{author}</text>
          <text className="isbnText">ISBN: {isbn}</text>
          <text className="bookType">{format}</text>
        </div>
        <div className="middleRight">
          <div className="ratingHolder">
            <Rating
              name="rating"
              value={rate}
              max={5}
              precision={0.2}
              readOnly
            />
            <span>{rate.toFixed(1)}</span>
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
            {copiesLeft}/{totalCopies}
          </text>
        </div>
        <div>
          {(copiesLeft > 0 && !hideBorrow) && (
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
