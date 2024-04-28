import './AdminItemCard.css';
import { useNavigate } from "react-router-dom";


export const AdminItemCard = ({ apiList }) => {
    const navigate = useNavigate();

    const handleButtonClick = (api) => {
      navigate(`/${api.toLowerCase().replace(" ", "-")}`);
    };
  
    return (
      <>
        {apiList.map((api, index) => (
          <li key={index}>
            <div className="card">
              <div className="card-details">
                <p className="text-title">{api}</p>
                  <p className="text-body">View All</p>
              </div>
              <button className="card-button" onClick={() => handleButtonClick(api)}>Click</button>
            </div>
          </li>
        ))}
      </>
    );
}