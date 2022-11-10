import Footer from "./Footer";
import "./styling/LayoutStyling.css";

/***
 *
 * @param children are all elements of main body of application
 * @return {JSX.Element} a look of application
 */
const Layout = ({ children }) => {
  return (
    <>
      <main>{children}</main>
      <Footer />
    </>
  );
};

export default Layout;
