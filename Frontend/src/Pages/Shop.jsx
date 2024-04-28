import React, { useEffect } from 'react'
import { Header } from '../components/Header'
import { Footer } from '../components/Footer'
import { ListProduct } from '../components/HomeComponent/ListProducts'

const Shop = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);

  return (
    <>
    <Header/>
    <ListProduct/>
    <Footer/>
    </>
  )
}
export default Shop;