import { useState } from 'react';
import axios from 'axios';
import './Login.scss';
import { useNavigate } from 'react-router-dom';

const mode = 'login';

function LoginComponent(props) {
  const [mode, setMode] = useState(props.mode);
  const [errorMessage, setErrorMessage] = useState('');

  const navigate = useNavigate();

  function submitLogin(e) {
    e.preventDefault();
    // use spring route to get credential validation
    //axios.get("https://localhost:8080/api");

    //api call returns 200, credentials are valid
    if (e.target[0].value === 'admin' && e.target[1].value === '123456') {
      localStorage.setItem('isAuthenticated', 'true');
      navigate('/customer');
    }
    //else show error on frontend
    else {
      setErrorMessage('Invalid username/password');
      return;
    }
  }

  function submitSignup(e) {
    e.preventDefault();
    console.log('login submit');
    // use spring route to create new user in database
    //axios.post("https://localhost:8080/api");

    //only navigate when login is successful
    navigate('/customer');
  }

  return (
    <div>
      <div
        className={`form-block-wrapper form-block-wrapper--is-${mode}`}
      ></div>
      <section className={`form-block form-block--is-${mode}`}>
        <header className='form-block__header'>
          <h1>{mode === 'login' ? 'Welcome back!' : 'Sign up'}</h1>
          <div className='form-block__toggle-block'>
            <span>
              {mode === 'login' ? "Don't" : 'Already'} have an account? Click
              here &#8594;
            </span>
            <input
              id='form-toggler'
              type='checkbox'
              onClick={() => setMode(mode === 'login' ? 'signup' : 'login')}
            />
            <label htmlFor='form-toggler'></label>
          </div>
        </header>
        <LoginForm
          mode={mode}
          onSubmit={mode === 'login' ? submitLogin : submitSignup}
        />
        {errorMessage.length > 0 ? <p>{errorMessage}</p> : null}
      </section>
    </div>
  );
}

function LoginForm(props) {
  return (
    <form onSubmit={props.onSubmit}>
      <div className='form-block__input-wrapper'>
        <div className='form-group form-group--login'>
          <Input
            type='text'
            id='username'
            label='username'
            disabled={props.mode === 'signup'}
          />
          <Input
            type='password'
            id='password'
            label='password'
            disabled={props.mode === 'signup'}
          />
        </div>
        <div className='form-group form-group--signup'>
          <Input
            type='text'
            id='firstName'
            label='first name'
            disabled={props.mode === 'login'}
          />
          <Input
            type='text'
            id='lastName'
            label='last name'
            disabled={props.mode === 'login'}
          />
          <Input
            type='date'
            id='DOB'
            label='date of birth'
            disabled={props.mode === 'login'}
          />
          <Input
            type='email'
            id='email'
            label='email'
            disabled={props.mode === 'login'}
          />
          <Input
            type='password'
            id='createPassword'
            label='password'
            disabled={props.mode === 'login'}
          />
          <Input
            type='password'
            id='repeatPassword'
            label='repeat password'
            disabled={props.mode === 'login'}
          />
        </div>
      </div>
      <button
        className='custom-button custom-button--primary full-width'
        type='submit'
      >
        {props.mode === 'login' ? 'Log In' : 'Sign Up'}
      </button>
      <div></div>
    </form>
  );
}

const Input = ({ id, type, label, disabled }) => (
  <input
    className='form-group__input'
    type={type}
    id={id}
    placeholder={label}
    disabled={disabled}
  />
);

function LoginPage(props) {
  return (
    <div className={`custom-login app--is-${mode}`}>
      <LoginComponent mode={mode} />
    </div>
  );
}

export default LoginPage;
