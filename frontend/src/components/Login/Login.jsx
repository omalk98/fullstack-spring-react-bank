import axios from 'axios';
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { useFormik } from 'formik';
import './Login.scss'

const mode = 'login';

function LoginComponent(props) {
    const [mode, setMode] = useState(props.mode);
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    function submitLogin(e) {
        e.preventDefault()
        console.log('login works')
        
        // use spring route to get credential validation
        //axios.get("http://localhost:8080/api").then(res=>console.log(res.data));

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
        e.preventDefault()
        console.log('signup works')
        // use spring route to create new user in database
        //axios.post("http://localhost:8080/api");
        //only navigate when login is successful
        navigate('/customer');
    }

    return (
        <div>
            <div className={`form-block-wrapper form-block-wrapper--is-${mode}`} ></div>
            <section className={`form-block form-block--is-${mode}`}>
                <header className="form-block__header">
                    <h1>{mode === 'login' ? 'Welcome back!' : 'Sign up'}</h1>
                    <div className="form-block__toggle-block">
                        <span>{mode === 'login' ? 'Don\'t' : 'Already'} have an account? Click here &#8594;</span>
                        <input id="form-toggler" type="checkbox" onClick={() => setMode(mode === 'login' ? 'signup' : 'login')} />
                        <label htmlFor="form-toggler"></label>
                    </div>
                </header>
                <LoginForm mode={mode} onSubmit={mode === "login" ? submitLogin : submitSignup} />
                {errorMessage.length > 0 ? <p>{errorMessage}</p> : null}
            </section>
        </div>
    )
}

function LoginForm(props) {

const formik = useFormik({
    initialValues : {
        username : "",
        password : "",

        firstName : "",
        lastName : "",
        email : "",
        dob : "",
        createPassword : "",
        repeatPassword : ""
    }
});

    return (
        <form onSubmit={props.onSubmit}>
            <div className="form-block__input-wrapper">
                <div className="form-group form-group--login">
                    <Input type="text" id="username" label="username" disabled={props.mode === 'signup'} onChange={formik.handleChange} value={formik.values.username} />
                    <Input type="password" id="password" label="password" disabled={props.mode === 'signup'} onChange={formik.handleChange} value={formik.values.password} />
                </div>
                <div className="form-group form-group--signup">
                    <Input type="text" id="firstName" label="first name" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.firstName} />
                    <Input type="text" id="lastName" label="last name" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.lastName} />
                    <Input type="date" id="dob" label="date of birth" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.dob} />
                    <Input type="email" id="email" label="email" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.email} />
                    <Input type="password" id="createPassword" label="password" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.createPassword} />
                    <Input type="password" id="repeatPassword" label="repeat password" disabled={props.mode === 'login'} onChange={formik.handleChange} value={formik.values.repeatPassword} />
                </div>
            </div>
            <button className="custom-button custom-button--primary full-width" type="submit">{props.mode === 'login' ? 'Log In' : 'Sign Up'}</button>
            <div></div>
        </form>
    )
}

const Input = ({ id, type, label, disabled, onChange, value }) => (
    <input className="form-group__input" type={type} id={id} placeholder={label} disabled={disabled} onChange={onChange} value={value} />
);

function Login(props) {
    return (
        <div className={`custom-login app--is-${mode}`}>
            <LoginComponent
                mode={mode}
            />
        </div>
    );
}

export default Login;