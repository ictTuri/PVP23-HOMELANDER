import { Route, Routes } from "react-router-dom";
import Navigation from "./routes/navigation/navigation.component";
import Home from "./routes/home/home.component";
import Login from "./components/authentication/login.component";
import Register from "./components/authentication/register.component";
import Profile from "./components/profile/profile.component";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigation />}>
        <Route index element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="user/profile" element={<Profile />} />
      </Route>
    </Routes>
  );
}

export default App;
