import styles from "./Footer.module.css"

import { Texts } from './footerData';

function Footer(){
    return(
        <>
        <footer className={styles.footer}>
            <h1>{Texts.header1}</h1>
            <p>{Texts.text1}</p>
            <h1>{Texts.header2}</h1>
            <p>{Texts.text2}</p>
            <p>{Texts.text3}</p>
            <p>{Texts.text4}</p>
            <p>{Texts.text5}</p>
            <p>{Texts.contacts}</p>            
        </footer>
        <div className={styles.copyright}>
            <p>&copy; {new Date().getFullYear()} {Texts.copyright}</p>
        </div>
        </>        
    );
}

export default Footer