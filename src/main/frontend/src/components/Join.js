import React, {useEffect} from "react";
import styles from "../styles/css/Join.module.css";

function Join() {
  //회원가입,로그아웃 완료 페이지
    useEffect((v) => {
        const token = new URL(window.location.href).searchParams.get("accessToken")
        const name = new URL(window.location.href).searchParams.get("name")
        const email = new URL(window.location.href).searchParams.get("email")

        if (token) {
            localStorage.setItem("token", token)
            localStorage.setItem("name", name)
            localStorage.setItem("email", email)
        }
        }
    )
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

export default Join;
