import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './ProductDetail.css'; // CSS 파일 임포트

const ProductDetail = () => {
    const { pnum } = useParams();
    const [product, setProduct] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/products/${pnum}`)
            .then(response => response.json())
            .then(data => {
                console.log(data); // 콘솔 로그 추가
                setProduct(data);
            })
            .catch(error => console.error('Error fetching product:', error));
    }, [pnum]);

    if (!product) {
        return <div>Loading...</div>;
    }

    return (
        <div className="product-detail">
            <div className="product-detail-header">
            </div>
            <div className='sec'>
                <img src={product.pimgUrl} alt={product.pname} className='prodImg'/>
                <div className='detail'>
                    <h3 className="title">{product.pname}</h3>
                    <div className="price">{product.pprice.toLocaleString()}원</div>
                    <div className="count">재고 수량: {product.pcount}개</div>
                    <div className="date">업데이트: {new Date(product.pdate).toLocaleString()}</div>
                    <button type="submit" className='cartBtn'>장바구니</button>
                    <button type="submit" className='buyBtn'>구매하기</button>
                </div>
            </div>
            {product.pdetailImgUrl ? (
                <img src={product.pdetailImgUrl} alt="상세 이미지" className='prodDetail'/>
            ) : (
                <div>No detailed image available</div>
            )}
            <div className="product-detail-footer">
                footer
            </div>
        </div>
    );
};

export default ProductDetail;
