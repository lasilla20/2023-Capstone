import React, { Component } from "react";
import styles from "../styles/css/Logout.module.css";

class Logout extends Component {
  //로그아웃 페이지
  render() {
    return (
      <div className={styles.container}>
        <img alt="" src={"img/임시로고.svg"} />
        <br></br>
        <br></br>
        <div className={styles.h2}> 로그아웃 하시겠습니까? </div>
        <br></br>
        <div>
          <button className={styles.no}> 취소 </button>
          <button className={styles.yes}> 확인 </button>
        </div>
      </div>
    );
  }
}

export default Logout;
