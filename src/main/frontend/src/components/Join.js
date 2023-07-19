import React, { Component } from "react";
import styles from "../styles/css/Join.module.css";

class Join extends Component {
  //회원가입,로그아웃 완료 페이지
  render() {
    return (
      <div className={styles.container}>
        <img alt="" src={"img/임시로고.svg"} />
        <br></br>
        <br></br>
        <div className={styles.h2}> 로그인/가입완료 </div>
        <br></br>
        <div className={styles.h3}> 환영합니다! </div>
        <br></br>
        <div>
          <button className={styles.cont}> 계속하기 </button>
        </div>
      </div>
    );
  }
}

export default Join;
