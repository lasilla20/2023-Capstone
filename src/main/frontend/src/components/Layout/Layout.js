import Footer from "./Footer";
import Header from "./Header";
import Sidebar from "./Sidebar";
import styles from "../../styles/css/ContentDetail.module.css";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <div>
      <Header></Header>
      <Footer></Footer>
      <Outlet></Outlet>
      <div className={styles.divsidebar}>
        <Sidebar></Sidebar>
      </div>
    </div>
  );
};

export default Layout;
