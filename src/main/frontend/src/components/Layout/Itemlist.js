import React, { useState, useEffect } from "react";
import axios from "axios";
import styles from "../../styles/css/ItemList.module.css";
import { ListItem } from "../ListItem";

//글자수 제한 함수
const truncate = (str, n) => {
  return str?.length > n ? str.substr(0, n - 1) + "..." : str;
};

const ItemList = ({ selectedCategoryId}) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    try {
      // 요청이 시작 할 때에는 error 와 users 를 초기화하고
      setError(null);
      setData(null);
      // loading 상태를 true 로 바꿉니다.
      setLoading(true);
      const response = await axios.get("/api");
      setData(response.data); // 데이터는 response.data 안에 들어있습니다.
    } catch (e) {
      setError(e);
    }
    setLoading(false);
  };


  const fetchCategoryData = async (categoryId) => {
    try {
      setError(null);
      setData(null);
      setLoading(true);
      // axios 등을 이용하여 categoryId를 이용하여 서버에서 데이터를 가져옵니다.
      //const response = await axios.get(`/api?categoryId=${categoryId}`);
      //alert(categoryId+"itemlist.js");
      const response = await axios.get(`/api/B/${categoryId}/1`);
      setData(response.data);
    } catch (e) {
      setError(e);
    }
    setLoading(false);
  };
  {
    /*useEffect(() => {
    fetchData();
  }, []);*/
  }

  useEffect(() => {
    if (selectedCategoryId !== null) {
      fetchCategoryData(selectedCategoryId);
    } else {
      fetchData();
    }
  }, [selectedCategoryId]);

  // 카테고리 선택을 처리하는 함수
  /*  const handleListItemClick = (categoryId) => {
    alert(categoryId);
  };*/

  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러가 발생했습니다</div>;
  if (!data) return null;
  return (
    <div className={styles.itemlistcontent}>
      {data.map((item) => (
        <ListItem
          className={styles.listItem}
          key={item.name}
          id={item.id}
          store={item.market}
          price={item.price}
          title={truncate(item.name, 10)}
          src={item.image}
          heartCnt={item.hearts}
        />
      ))}
    </div>
  );
};

export default ItemList;
