import PropTypes from "prop-types";
import React from "react";
import { motion } from "framer-motion";
import styles from "../styles/css/Category.module.css";

export const CategoryContent = ({ text = "여성패션"}) => {
    const selectCategory = (event) => {
        const buttonText = event.target.value;
        alert(buttonText);
        window.location.reload();
      };

    return (
    <motion.div layout
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
    >
        <div className={styles.categoryContent}>
            <ul id={styles.leftToRight}>
                <li><input className={styles.contentBtn} type="submit" value={text} onClick={selectCategory} /></li>
            </ul>        
        </div>
    </motion.div>
  );
};

CategoryContent.propTypes = {
  text: PropTypes.string,
};
