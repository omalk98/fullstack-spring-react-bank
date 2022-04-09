import './App.css';
import Customer from './components/Customer/Customer';
import NotFound from './components/Not Found/NotFound';
import Login from './components/Login/Login';
import Deposit from './components/Deposit/Deposit';
import Withdraw from './components/Withdraw/Withdraw';
import Balance from './components/Balance/Balance';
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute';
import Transfer from './components/Transfer/Transfer';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from 'react';

//the login page will make an api call to
//obtain all the customer data like name, username, account numbers,
//and balance
//we will set the state(loggedInCustomer)
//of App component to the one set within the login page
//by passing a function as a prop to the login page
//and then pass all the customer data to all the components

//add nav bar if necessary
function App() {

  const[user, setUser] = useState({
    id : 0,
    firstName : '',
    lastName : '',
    email : '',
    username : '',
    userRole : "",
    access_token : "",
    refresh_token : ""
  });

  return (
      <Router>
        {/* <NavBar/> */}
        <Routes>
          <Route path='/' element={<Login user={{details : user, setter : setUser}} />} />
          <Route
            path='/customer'
            element={
              <ProtectedRoute>
                <Customer user={user} />
              </ProtectedRoute>
            }
          />
          <Route
            exact
            path='/customer/deposit'
            element={
              <ProtectedRoute>
                <Deposit />
              </ProtectedRoute>
            }
          />
          <Route
            exact
            path='/customer/withdraw'
            element={
              <ProtectedRoute>
                <Withdraw />
              </ProtectedRoute>
            }
          />
          <Route
            exact
            path='/customer/transfer'
            element={
              <ProtectedRoute>
                <Transfer />
              </ProtectedRoute>
            }
          />
          <Route
            exact
            path='/customer/balance'
            element={
              <ProtectedRoute>
                <Balance />
              </ProtectedRoute>
            }
          />
          <Route path='*' element={<NotFound />} />
        </Routes>
      </Router>
  );
}

export default App;
