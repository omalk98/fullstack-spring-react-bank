import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useFormik } from 'formik';
import qs from 'qs';
import './Login.scss';

const mode = 'login';

function LoginComponent(props) {
  const [errorMessage, setErrorMessage] = useState('');

  const navigate = useNavigate();

  function submitSignup(e) {
    e.preventDefault();
    console.log('signup works');
    // use spring route to create new user in database
    //axios.post("http://localhost:8080/api");
    //only navigate when login is successful
    navigate('/customer');
  }

  return (
    <div>
      <div
        className={`form-block-wrapper form-block-wrapper--is-${props.mode}`}
      ></div>
      <section className={`form-block form-block--is-${props.mode}`}>
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
              onClick={() => props.setMode(props.mode === 'login' ? 'signup' : 'login')}
            />
            <label htmlFor='form-toggler'></label>
          </div>
        </header>
        <LoginForm mode={props.mode} setErrorMessage={setErrorMessage} setUser={props.setUser} />
        {errorMessage.length > 0 ? <p>{errorMessage}</p> : null}
      </section>
    </div>
  );
}

function LoginForm(props) {
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',

      firstName: '',
      lastName: '',
      email: '',
      dob: '',
      createPassword: '',
      repeatPassword: '',
    },
    onSubmit: (values) => {
      if (props.mode === 'login') {
        axios({
          method: 'post',
          url: 'http://localhost:8080/login',
          headers: {
            Authorization: 'Basic Og==',
            'Content-Type': 'application/x-www-form-urlencoded',
          },
          data: qs.stringify({
            username: values.username,
            password: values.password,
          }),
        })
          //api call returns 200, credentials are valid
          .then((res) => {
            console.log(res.data);
            localStorage.setItem('isAuthenticated', 'true');

            let user = {
              id: res.data?.user?.id,
              firstName: res.data?.user?.firstName,
              lastName: res.data?.user?.lastName,
              email: res.data?.user?.email,
              username: res.data?.user?.username,
              userRole: res.data?.user?.userRole,
              access_token: res.data?.access_token,
              refresh_token: res.data?.refresh_token,
            };

            props.setUser(user);

            localStorage.setItem('user', JSON.stringify(user));

            props.setErrorMessage('');
            navigate('/customer');
          })
          //else show error on frontend
          .catch((error) => {
            console.log(error);
            props.setErrorMessage('Invalid username/password');
          });
      }
      else if (props.mode === 'signup') {
        axios({
          method: 'post',
          url: 'http://localhost:8080/api/user/registration/register',
          headers: {
            'Content-Type': 'application/json'
          },
          data: JSON.stringify({
            "firstName": values.firstName,
            "lastName": values.lastName,
            "email": values.email,
            "username": values.username,
            "password": values.createPassword,
            "dateOfBirth": values.dob
          })
        })
          .then((res) => {
            console.log(res.data);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  });

  return (
    <form onSubmit={formik.handleSubmit}>
      <div className='form-block__input-wrapper'>
        <div className='form-group form-group--login'>
          <Input
            type='text'
            id='username'
            label='username'
            disabled={props.mode === 'signup'}
            onChange={formik.handleChange}
            value={formik.values.username}
          />
          <Input
            type='password'
            id='password'
            label='password'
            disabled={props.mode === 'signup'}
            onChange={formik.handleChange}
            value={formik.values.password}
          />
        </div>
        <div className='form-group form-group--signup'>
          <Input
            type='text'
            id='firstName'
            label='first name'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.firstName}
          />
          <Input
            type='text'
            id='lastName'
            label='last name'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.lastName}
          />
          <Input
            type='date'
            id='dob'
            label='date of birth'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.dob}
          />
          <Input
            type='email'
            id='email'
            label='email'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.email}
          />
          <Input
            type='password'
            id='createPassword'
            label='password'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.createPassword}
          />
          <Input
            type='password'
            id='repeatPassword'
            label='repeat password'
            disabled={props.mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.repeatPassword}
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

const Input = ({ id, type, label, disabled, onChange, value }) => (
  <input
    className='form-group__input'
    type={type}
    id={id}
    placeholder={label}
    disabled={disabled}
    onChange={onChange}
    value={value}
  />
);

function Login(props) {
  const [currentMode, setMode] = useState(mode);
  return (
    <div className={`custom-login app--is-${currentMode}`}>
      <LoginComponent mode={currentMode} setMode={setMode} setUser={props.setUser} />
    </div>
  );
}

export default Login;
