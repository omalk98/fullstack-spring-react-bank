import './App.css';
import Customer from './components/Customer/Customer';
import NotFound from './components/Not Found/NotFound';
import Login from './components/Login/Login';
import Deposit from './components/Deposit/Deposit';
import Withdraw from './components/Withdraw/Withdraw';
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
      <Router>
        {/* <NavBar/> */}
        <Routes>
          {/* <Route exact path='/*' element={<Customer />} />
          <Route exact path='/login' element={<Customer />} /> */}
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
          <Route path='*' element={<NotFound />} />
        </Routes>
      </Router>
  );
}

export default App;