import { legacy_createStore as createStore } from "redux";

const initialState = {
  id: "",
  emailId: "",
  lastName: "",
  firstName: "",
  dob: ""
};

const autentication = (state = initialState, action) => {
  if (action.type === "Login") {
    return action.payload;
  } else if (action.type === "Logout") {
    return initialState;
  } else {
    return initialState;
  }
};

const auth = createStore(autentication);

export const checkAuth = () => {
  const state = auth.getState();
  return (state.lastName === "") ? false : true;
}

export { auth };
