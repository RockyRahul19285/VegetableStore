

export const SetLocalStorage = ( key, value ="") => {
  // Set value to local storage
  localStorage.setItem(key, value);
  
  // No need to render anything
  return "element assigned "+value;
}

export const GetLocalStorage = (key) => {
  // Get value from local storage
  const value = localStorage.getItem(key);

  // Render the retrieved value
  return value;
}

export const RemoveLocalStorage =(key)=>{
  localStorage.removeItem(key);
}


