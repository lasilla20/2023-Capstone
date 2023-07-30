import styles from "../styles/css/ContentDetail.module.css";
import React, { useState, useEffect, useParams } from "react";

import HeartButton from "./HeartButton";
import axios from "axios";

const url = "https://web.joongna.com/"; //상품의 원글 url- producturl

const ContentDetail = (props) => {
  /*
  let { itemId } = useParams();
  let find = props.products.find(
    (product) => parseInt(product.itemId) === parseInt(itemId)
  );
  console.log(find);
  */
  const [heart, setHeart] = useState(false);

  //찜하기/해제 작동되는지 백이랑 통합 후 확인가능
  /*
  const fetchData = async () => {
    try {
      const res = await axios.get("http//localhost:8080/product/heartbutton"); //url 뭐 넣어야될지 모르겠다..
      if (res.data.type === "heart") setHeart(true);
    } catch (e) {
      console.log(e);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);

  const toggleLike = async (e) => {
    const res = await axios.post("http//localhost:8080/product/heartbutton"); // 좋아요 누름 -> DB 갱신
    setHeart(!heart);
  };
*/
  const toggleLike = async (e) => {
    setHeart(!heart);
  };

  {
    /*
  const lsts = localStorage.getItem("watched");
  useEffect(() => {
    let lst = JSON.parse(lsts);
    let dtail = product.itemId;
    if (!lst.length === 5) {
      Array.shift();
    }
    lst.push(dtail);
    lst = new Set(lst);
    lst = Array.from(lst);
    localStorage.setItem("watched", JSON.stringify(lst));
  }, []);
  // 최근 본  상품.
  // detail 들어가면 product id를 watched에 추가
*/
  }

  return (
    <div className={styles.div}>
      <img className={styles.icon} alt="" src="/img/빈 이미지.svg" />
      <div className={styles.parent}>
        <b className={styles.title}>제목</b>
        <b className={styles.price}>120,000원</b>
        <div className={styles.category}>{`홈 > 여성의류 > 신발`}</div>
        <div className={styles.name}>닉네임</div>
        <div className={styles.date}>5분 전</div>
        <div className={styles.views}>조회 20000</div>
        <div className={styles.heart}>찜 25</div>
      </div>
      <HeartButton heart={heart} onClick={toggleLike}></HeartButton>

      <div
        className={styles.btn_golink}
        onClick={() => {
          window.open(url);
        }}
      >
        <div className={styles.child} />
        <div className={styles.div7}>보러 가기</div>
      </div>

      <div className={styles.div8}>여기는 본문 긁어온 것...</div>
      <div className={styles.div9}>
        <p className={styles.p}>거래거래</p>
        <p className={styles.p}>대충 거래글 끝</p>
      </div>
      <div className={styles.line}></div>
      <div className={styles.group}>
        <img className={styles.icon5} alt="" src="/img/빈 이미지.svg" />
        <div className={styles.div12}>
          <div className={styles.inner} />
          <img className={styles.div13} alt="" src="/img/right-side.svg" />
        </div>
        <div className={styles.div14}>
          <div className={styles.inner} />
          <img className={styles.div15} alt="" src="/img/left-side.svg" />
        </div>
      </div>
      <div className={styles.container}>
        <div className={styles.div20}>중고나라</div>
        <div className={styles.div21}>
          <img
            className={styles.icon6}
            alt=""
            src="/img/중고나라 아이콘 1.png"
          />
        </div>
      </div>
    </div>
  );
};

export default ContentDetail;
