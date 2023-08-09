import React from "react";
import styled from "styled-components";

/*
새로추가
-total: 총 게시물 수
-setPage() 함수를 호출하여 page 상태 변경
  => <ItemList/> 컴포넌트는 새로운 페이지 번호에 해당하는 게시물 범위를 계산하여 다시 화면을 렌터링

*/

function Pagination({ total, limit, page, setPage }) {
  const numPages = Math.ceil(total / limit); //필요한 페이지의 개수

  return (
    <Nav>
      <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
        &lt;
      </Button>
      {Array(numPages)
        .fill()
        .map((_, i) => (
          <Button
            key={i + 1}
            onClick={() => setPage(i + 1)}
            aria-current={page === i + 1 ? "page" : null}
          >
            {i + 1}
          </Button>
        ))}
      <Button onClick={() => setPage(page + 1)} disabled={page === numPages}>
        &gt;
      </Button>
    </Nav>
  );
}

const Nav = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;
  margin: 16px;
`;

const Button = styled.button`
  border: none;
  border-radius: 8px;
  padding: 8px;
  margin: 0;
  background: black;
  color: white;
  font-size: 1rem;

  &:hover {
    background: tomato;
    cursor: pointer;
    transform: translateY(-2px);
  }

  &[disabled] {
    background: grey;
    cursor: revert;
    transform: revert;
  }

  &[aria-current] {
    background: #5d1fc2;
    font-weight: bold;
    cursor: revert;
    transform: revert;
  }
`;

export default Pagination;
