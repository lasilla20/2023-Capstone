import Category from "./Layout/Category";
import ItemList from "./Layout/Itemlist";
import styles from "../styles/css/ContentDetail.module.css";

const Home = () => {
  return (
    <div>
      <div className={styles.divcategory}>
        <Category></Category>
      </div>
      <div className={styles.divcontent}>
        <ItemList></ItemList>
      </div>
    </div>
  );
};
export default Home;
