import styles from "../../styles/css/Header.module.css";
import React, { useState } from "react";

export default function Searchbar() {
  {
    /* react 내 검색 test
    const Info = [
        {
            "name" : "어쩌구 신발",
            "cost" : "4,000원"
        },
        {
            "name" : "어쩌구 가방",
            "cost" : "3,000원"
        },
    ]*/
  }

  const [word, setWord] = useState("");

  const onSubmit = async () => {
    window.location.href = "/search/" + word;
  };

  return (
    <div>
      <div className={styles.groupItem} />
      <button
        className={styles.searchAltIcon}
        type="submit"
        onClick={() => {
          onSubmit();
        }}
      >
        <img alt="" src="/img/search-alt.svg" />
      </button>
      <input
        className={styles.div16}
        name="keyword"
        type="text"
        placeholder="상품명 입력"
        onChange={(e) => {
          setWord(e.target.value);
          console.log(word);
        }}
      />

      {/* react 내 검색 test
        {Info.filter((info) =>{
            if(search===""){
                return info;
            } else if (info.name.toLowerCase().includes(search.toLowerCase())){
                return info;
            }}).map((info, index)=>{
                return (<div className={styles.pannel}>
                    <div className={styles.prod}
                    key = {index}>
                    <p>{info.name}</p>
                    <p>{info.cost}</p>
                    </div>
                    </div>
                );
            })}*/}
    </div>
  );
}
