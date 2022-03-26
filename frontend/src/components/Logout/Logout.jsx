import React from 'react';
import { Button } from 'react-bootstrap';

export default function Logout() {
  localStorage.setItem('isAuthenticated', false);
  return (
    <Button href={`/`} variant='success' size='lg'>
      Logout
    </Button>
  );
}
