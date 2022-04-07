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

//the login page will make an api call to
//obtain all the customer data like name, username, account numbers,
//and balance
//we will set the state(loggedInCustomer)
//of App component to the

//add nav bar if necessary
function App() {
  return (
    <>
      <Router>
        {/* <NavBar/> */}
        <Routes>
          <Route path='/' element={<Login />} />
          <Route
            path='/customer'
            element={
              <ProtectedRoute>
                <Customer />
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
    </>
  );
}

export default App;
