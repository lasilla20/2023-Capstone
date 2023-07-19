import React from "react";
import styles from "../styles/css/ContentDetail.module.css";

const HeartButton = ({ heart, onClick }) => {
  return (
    <div
      className={heart ? styles.btn_unheart : styles.btn_heart}
      onClick={onClick}
    >
      <div className={heart ? styles.div17 : styles.div177}>
        {heart ? <p>찜해제</p> : <p>찜하기</p>}
      </div>
    </div>
  );
};

export default HeartButton;
