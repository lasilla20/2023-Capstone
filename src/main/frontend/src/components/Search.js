import { useParams } from "react-router";
import { useState, useEffect } from "react";
import axios from "axios";
import styles from "../styles/css/Header.module.css";

export default function Search() {
  const [searchData, setSearchData] = useState([]);
  const params = useParams();

  const onSubmit = async (itemid) => {
    window.location.href = "/product/" + itemid;
  };

  useEffect(() => {
    async function fetchData() {
      const result = await axios.get(
        "http://localhost:3000/product/search?word=" + params.word
      );
      console.log(result.data.result);
      setSearchData(result.data.result);
    }
    fetchData();
  }, []);

  {
    searchData.map((product) => {
      return (
        <div className={styles.pannel}>
          <div className={styles.prod}>
            <p>{product.img}</p>
            <p>{product[4]}</p>
            <button
              type="submit"
              onClick={() => {
                onSubmit(product.id);
              }}
            >
              {product.name}
            </button>
            <p>{product.price}</p>
          </div>
        </div>
      );
    });
  }
}
