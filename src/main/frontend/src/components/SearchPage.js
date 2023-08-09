import Category from "./Layout/Category";
import Search from "../components/Search.js";
import styles from "../styles/css/ContentDetail.module.css";

const SearchPage = () => {
  return (
    <div className={styles.divhome}>
      <div className={styles.divcategory}>
        <Category></Category>
      </div>
      <div className={styles.divcontent}>
        <Search></Search>
      </div>
    </div>
  );
};
export default SearchPage;
