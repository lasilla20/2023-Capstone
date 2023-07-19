import { useEffect } from "react";
import styles from "../../styles/css/Sidebar.module.css";
import { useNavigate } from "react-router-dom";

function Sidebar(props) {
  //props에 detail item 넣어줘야함. item img 경로 정해지면 변경.
  const navigate = useNavigate();

  if (!localStorage.getItem("watched")){
    localStorage.setItem("watched", JSON.stringify([]));}
  // 처음에 최근 본 상품이 아무것도 없으면 watched 만듬.

  const lsts = localStorage.getItem("watched");
  const lstss = JSON.parse(lsts).reverse();
{/*
  useEffect(() => {
    let lst = JSON.parse(lsts);
    let dtail = props[0];
    if (!lst.length === 5) {
      Array.shift();
    }
    lst.push(dtail);
    lst = new Set(lst);
    lst = Array.from(lst);
    localStorage.setItem("watched", JSON.stringify(lst));
  }, []);
  // detail 들어가면 product id를 watched에 추가
*/}
  return (
    <div className={styles.div}>
      <div className={styles.box}>
        <div className={styles.title}>최근 본 상품</div>
        {lstss.map((itemid) => {
          return (
            <img
              className={styles.item}
              alt=""
              src="/img/빈 이미지.svg"
              style={{ margin: 5 }}
              onClick={() => {
                navigate(`/product/${itemid}`);
              }}
            />
          );
        })}
      </div>
    </div>
  );
}

export default Sidebar;
