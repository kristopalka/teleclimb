import {View, Text} from "react-native";
import {styles} from "../styles";

export function History ({navigation}) {
    return(
        <View>
            <Text style={styles.title}>
                Tutaj będzie lista wszystkich wysędziowanych przejść
            </Text>
        </View>
    );
}