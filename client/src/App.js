import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import React, { useEffect, useState } from "react";
import { ProductList } from "./components/ProductList";
import { Summary } from "./components/Summary";
import axios from "axios";

function App() {
  // 상태에 접근할 수 있는 변수명, 상태값 바꾸는 함수
  const [products, setProducts] = useState([
    {
      productId: "1",
      productName: "콜롬비아커피1",
      category: "커피빈",
      price: 3000,
    },
    {
      productId: "2",
      productName: "콜롬비아커피2",
      category: "커피빈",
      price: 3000,
    },
    {
      productId: "3",
      productName: "콜롬비아커피3",
      category: "커피빈",
      price: 3000,
    },
  ]); //기본상태
  const [items, setItems] = useState([]);
  const handleAddClicked = (productId) => {
    //같은 아이디 찾기
    const product = products.find((v) => v.productId === productId);
    const found = items.find((v) => v.productId === productId);
    const updatedItems =
      //상품확인...모든속성 복사, 개수 +1/없으면 기존 아이템 반환
      //기존것 복사 + 새로운 상품 추가
      found
        ? items.map((v) =>
            v.productId === productId ? { ...v, count: v.count + 1 } : v
          )
        : [
            ...items,
            {
              ...product,
              count: 1,
            },
          ];
    setItems(updatedItems);
  };

  //컴포넌트가 렌더링 될 때마다 특정 작업을 실행할 수 있도록 하는 Hook
  //비동기작업은 여기서
  useEffect(() => {
    axios.get("/api/v1/products").then((v) => setProducts(v.data));
  }, []); //빈배열 딱 한번 실행

  const handleOrderSubmit = (order) => {
    if (items.length === 0) {
      alert("아이템을 추가해 주세요!");
    } else {
      axios
        .post("/api/v1/orders", {
          email: order.email,
          address: order.address,
          postcode: order.postcode,
          orderItems: items.map((v) => ({
            productId: v.productId,
            category: v.category,
            price: v.price,
            quantity: v.count,
          })),
        })
        .then(
          (v) => alert("주문이 정상적으로 접수되었습니다."),
          (e) => {
            alert("서버 장애");
            console.error(e);
          }
        );
    }
  };

  return (
    <body className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">I'm FRUIT</h1>
      </div>
      <div className="card">
        <div className="row">
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <ProductList products={products} onAddClick={handleAddClicked} />
          </div>
          <div className="col-md-4 summary p-4">
            <Summary items={items} onOrderSubmit={handleOrderSubmit} />
          </div>
        </div>
      </div>
    </body>
  );
}

export default App;
