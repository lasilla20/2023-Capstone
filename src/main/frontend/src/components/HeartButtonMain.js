import React, { useState, useEffect } from "react";

import styled from "styled-components";
import FullHeartImg from "../assets/heart_full.png";
import EmptyHeartImg from "../assets/heart_empty.png";
import styles from "../styles/css/ItemList.module.css";

{
  /* 
const Heart = styled.img`
  width: 20px;
  height: 20px;
  color: red;
`; */
}

const HeartButtonMain = ({ like, onClick }) => {
  return (
    <img
      className={styles.heartButton}
      src={like ? FullHeartImg : EmptyHeartImg}
      onClick={onClick}
    />
  );
};

export default HeartButtonMain;
