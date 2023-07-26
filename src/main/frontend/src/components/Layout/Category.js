import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { CategoryContent } from "../CategoryContent";
import styles from "../../styles/css/Category.module.css";

/* 아코디언 컴포넌트 이용 */
const CategoryBox = () => {
  const [isAccordionOpen, setIsAccordionOpen] = useState(false);

  const toggleOpen = () => {
    setIsAccordionOpen(!isAccordionOpen);
  };

  return (
    <AnimatePresence>
      <motion.div
        layout
        className={styles.categorybox}
        onClick={toggleOpen}
        initial={{ borderRadius: 10 }}
      >
        <motion.div layout className={styles.accordionTitle}>
          <motion.img
            layout
            className={styles.avatar}
            alt=""
            src="/img/category-menu.svg"
          />
          <motion.div layout className={styles.ctitleLabel}>
            Category
          </motion.div>
        </motion.div>
        {isAccordionOpen && (
          <motion.div layout className={styles.accordionContent}>
            {/* Add your accordion content here */}
            <CategoryContent
              className={styles.categoryItem - 0}
              text="여성패션"
            />
            <CategoryContent
              className={styles.categoryItem - 1}
              text="남성패션"
            />
            <CategoryContent
              className={styles.categoryItem - 2}
              text="화장품 / 미용"
            />
            <CategoryContent
              className={styles.categoryItem - 3}
              text="가구 / 인테리어"
            />
            <CategoryContent
              className={styles.categoryItem - 11}
              text="문구류"
            />
            <CategoryContent 
              className={styles.categoryItem - 4} 
              text="식품" 
            />
            <CategoryContent
              className={styles.categoryItem - 5}
              text="출산 / 유아동"
            />
            <CategoryContent
              className={styles.categoryItem - 6}
              text="반려동물용품"
            />
            <CategoryContent
              className={styles.categoryItem - 7}
              text="생활(주방/가전)"
            />
            <CategoryContent
              className={styles.categoryItem - 8}
              text="디지털 / 컴퓨터"
            />
            <CategoryContent
              className={styles.categoryItem - 9}
              text="스포츠 / 레저"
            />
            <CategoryContent
              className={styles.categoryItem - 10}
              text="건강 / 의료용품"
            />
          </motion.div>
        )}

        {/*
    <button onClick={toggleOpen}>
        {isAccordionOpen ? "Close" : "Category"}
    </button>

      {isAccordionOpen && (
        <div className={styles.accordionContent}>
          <CategoryContent className={styles.categoryItem-0} text="여성패션"/>
          <CategoryContent className={styles.categoryItem-1} text="남성패션"/>
          <CategoryContent className={styles.categoryItem-2} text="화장품 / 미용"/>
          <CategoryContent className={styles.categoryItem-3} text="가구 / 인테리어"/>
          <CategoryContent className={styles.categoryItem-11} text="문구류"/>
          <CategoryContent className={styles.categoryItem-4} text="식품"/>
          <CategoryContent className={styles.categoryItem-5} text="출산 / 유아동"/>
          <CategoryContent className={styles.categoryItem-6} text="반려동물용품"/>
          <CategoryContent className={styles.categoryItem-7} text="생활(주방/가전)"/>
          <CategoryContent className={styles.categoryItem-8} text="디지털 / 컴퓨터"/>
          <CategoryContent className={styles.categoryItem-9} text="스포츠 / 레저"/>
          <CategoryContent className={styles.categoryItem-10} text="건강 / 의료용품"/>
        </div>
      )}
      */}
      </motion.div>
    </AnimatePresence>
  );
};
export default CategoryBox;
