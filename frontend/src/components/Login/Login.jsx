import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useFormik } from 'formik';
import qs from 'qs';
import './Login.scss';

const mode = 'login';

function LoginComponent(props) {
  const [mode, setMode] = useState(props.mode);
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
        <LoginForm setErrorMessage={setErrorMessage} user={props.user} />
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
          //data = JSON.parse(res.data);
          console.log(res.data);
          localStorage.setItem('isAuthenticated', 'true');

          props.user.setter({
            id: res.data?.user?.id,
            firstName: res.data?.user?.firstName,
            lastName: res.data?.user?.lastName,
            email: res.data?.user?.email,
            username: res.data?.user?.username,
            userRole: res.data?.user?.userRole,
            access_token: res.data?.access_token,
            refresh_token: res.data?.refresh_token,
          });

          localStorage.setItem(
            'user',
            JSON.stringify({
              id: res.data?.user?.id,
              firstName: res.data?.user?.firstName,
              lastName: res.data?.user?.lastName,
              email: res.data?.user?.email,
              username: res.data?.user?.username,
              userRole: res.data?.user?.userRole,
              access_token: res.data?.access_token,
              refresh_token: res.data?.refresh_token,
            })
          );

          props.setErrorMessage('');
          navigate('/customer');
        })
        //else show error on frontend
        .catch((error) => {
          console.log(error);
          props.setErrorMessage('Invalid username/password');
        });
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
            disabled={mode === 'signup'}
            onChange={formik.handleChange}
            value={formik.values.username}
          />
          <Input
            type='password'
            id='password'
            label='password'
            disabled={mode === 'signup'}
            onChange={formik.handleChange}
            value={formik.values.password}
          />
        </div>
        <div className='form-group form-group--signup'>
          <Input
            type='text'
            id='firstName'
            label='first name'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.firstName}
          />
          <Input
            type='text'
            id='lastName'
            label='last name'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.lastName}
          />
          <Input
            type='date'
            id='dob'
            label='date of birth'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.dob}
          />
          <Input
            type='email'
            id='email'
            label='email'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.email}
          />
          <Input
            type='password'
            id='createPassword'
            label='password'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.createPassword}
          />
          <Input
            type='password'
            id='repeatPassword'
            label='repeat password'
            disabled={mode === 'login'}
            onChange={formik.handleChange}
            value={formik.values.repeatPassword}
          />
        </div>
      </div>
      <button
        className='custom-button custom-button--primary full-width'
        type='submit'
      >
        {mode === 'login' ? 'Log In' : 'Sign Up'}
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
  return (
    <div className={`custom-login app--is-${mode}`}>
      <LoginComponent mode={mode} user={props.user} />
    </div>
  );
}

export default Login;
