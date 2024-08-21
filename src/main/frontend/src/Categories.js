import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Categories.css';

function Categories() {
    const [categories, setCategories] = useState([]);
    const [products, setProducts] = useState([]);
    const [sortOption, setSortOption] = useState('name');

    useEffect(() => {
        axios.get('/api/categories')
            .then(response => setCategories(response.data))
            .catch(error => console.error(error));

        axios.get('/api/products')
            .then(response => setProducts(response.data))
            .catch(error => console.error(error));
    }, []);

    const handleSortChange = (event) => {
        setSortOption(event.target.value);
        let sortedProducts = [...products];
        if (event.target.value === 'name') {
            sortedProducts.sort((a, b) => a.pName.localeCompare(b.pName));
        } else if (event.target.value === 'stock') {
            sortedProducts.sort((a, b) => b.pCount - a.pCount);
        }
        setProducts(sortedProducts);
    };

    return (
        <div className="container">
            <aside className="sidebar">
                <h2>카테고리</h2>
                <ul>
                    {categories.map(category => (
                        <li key={category.caNum}>
                            {category.caName}
                        </li>
                    ))}
                </ul>
            </aside>
            <main className="main-content">
                <div className="sort-options">
                    <label htmlFor="sort-by">정렬 기준:</label>
                    <select id="sort-by" value={sortOption} onChange={handleSortChange}>
                        <option value="name">가나다 순</option>
                        <option value="stock">재고량 순</option>
                    </select>
                </div>
                <div className="product-grid">
                    {products.map(product => (
                        <div className="product-card" key={product.pNum}>
                            <img src={product.pImgUrl} alt={product.pName} />
                            <h3>{product.pName}</h3>
                            <p className="price">{product.pPrice}원</p>
                            <p className="stock">재고: {product.pCount}</p>
                            <div className="buttons">
                                <button>장바구니 담기</button>
                                <button>바로 구매</button>
                            </div>
                        </div>
                    ))}
                </div>
            </main>
        </div>
    );
}

export default Categories;
