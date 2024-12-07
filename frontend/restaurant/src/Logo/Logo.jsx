import styles from "./logo.module.css"

import { Images } from './logoData';

function Logo(){
    return(<div className={styles.logo_container}>
        <img 
            className={styles.logo_image}
            src={Images.logo} 
            alt="Logo"
        />
    </div>   
    );
}

export default Logo