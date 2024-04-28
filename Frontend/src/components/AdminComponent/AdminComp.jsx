import { Footer } from "../Footer"
import { AdminHeader } from "./AdminHeader"
import { AdminItemCard } from "./AdminItemCard"

export const AdminComp = () => {
    const apiList = ["Admin API", "Customer API", "Orders API", "Product API"];

    return (
        <>
            <AdminHeader />
            <ul className="grid-list" style={{ display: "flex", flexDirection: "row", paddingBottom: '2rem', paddingLeft: '2rem' }}>
                <AdminItemCard apiList={apiList} />
            </ul>
            <Footer />
        </>

    )
}