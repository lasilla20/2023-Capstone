import styles from "../../styles/css/Header.module.css";
import Searchbar from "./Searchbar";

const Header = () => {
  return (
    <div className={styles.groupParent}>
      <div className={styles.planetParent}>
        <img className={styles.planet} alt="" src="/img/임시로고.svg" />
      </div>
      <img
        className={styles.basketAlt3Icon}
        alt=""
        src="/img/basket-alt-3.svg"
      />
      <img
        className={styles.userCicrleDuotoneIcon}
        alt=""
        src="/img/user-cicrle-duotone.svg"
      />
      <Searchbar></Searchbar>
      <div className={styles.line} />
      {/*<div className={styles.groupItem} />
      <img className={styles.searchAltIcon} alt="" src="/img/search-alt.svg" />
      <input className={styles.div16}
        placeholder="상품명 입력"
        />*/}
    </div>
  );
};

export default Header;
