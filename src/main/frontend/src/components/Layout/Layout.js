import Footer from "./Footer";
import Header from "./Header";
import Sidebar from "./Sidebar";
import styles from "../../styles/css/ContentDetail.module.css";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
<<<<<<< HEAD
    <div>
      <Header></Header>
      <div className={styles.wrap}>
        <Outlet></Outlet>
      </div>
      <Footer></Footer>
      <div className={styles.divsidebar}>
        <Sidebar></Sidebar>
=======
    <div className={styles.wrapper}>
      <div className={styles.contentWrapper}>
        <Header></Header>
        <Outlet></Outlet>
        <div className={styles.divsidebar}>
          <Sidebar></Sidebar>
        </div>
>>>>>>> c41551a991a342799ea9ae16d32775cebf92f326
      </div>

      <Footer></Footer>
    </div>
  );
};

export default Layout;
