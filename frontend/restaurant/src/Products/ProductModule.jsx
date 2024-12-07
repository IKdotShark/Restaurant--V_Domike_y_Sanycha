import React from 'react';
import ProductCard from './ProductCard';
import styles from './ProductModule.module.css';

function ProductModule({ title, products, titleColor }) {
  return (
    <section className={styles.module}>
      <h1 className={styles.title} style={{ color: titleColor }}>
        {title}
      </h1>
      <div className={styles.grid}>
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </section>
  );
}

export default ProductModule;
