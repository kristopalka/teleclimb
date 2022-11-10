import {Button, SafeAreaView, Text, TextInput, View, TouchableOpacity, StyleSheet, Image} from "react-native";
import {styles} from "../styles";
import {useState} from "react";
import axios from "axios";
import hash from "react-native-web/dist/vendor/hash";

export const RegisterRefereeScreen =({route, navigation})=> {

    const localStyles = StyleSheet.create({
        button: {
            backgroundColor: "black",
            padding: 10,
            alignItems: "center"
        },
        bigIcon: {
            height: 30,
            width: 30,
            marginTop: 5
        }
    });

    const {ip} = route.params;
    const [pin, onChangePin] = useState("");
    const [data, setData] = useState([]);
    let ipAddressAndPort = "http://" + ip + ":8080";

    async function getRefereeDataByRoundId(roundId) {
        if(validateJudgeId(roundId)) await fetchData(roundId);
    }

    function verifyData() {
        return data.length > 0;
    }

    async function fetchStarts() {
        await axios.get(ipAddressAndPort + "/mobile/starts-by-referee-position-hash/" + pin)
            .then(response => setData(response.data))
            .catch(error => console.log(error));
    }

    function validateJudgeId(judgeId) {
        return parseInt(judgeId) > 0;
    }

    function confirmation(verification) {

        if(verification) {
                return (
                    <View style={styles.centeredContainer}>
                        <View style={styles.horizontalFlex}>
                            <Text style={styles.confirmText}>Zweryfikowano pomyślnie</Text>
                            <Image source={require("../assets/ok_Icon.png")} style={localStyles.bigIcon}/>
                        </View>
                        <TouchableOpacity style={localStyles.button} onPress={() => navigation.navigate("Starty zawodników", {hash: pin, ip: ip, starts: data})}>
                            <Text style={styles.whiteSubtitle}>Przejdź do sędziowania</Text>
                        </TouchableOpacity>
                    </View>
                )
        }
        else {
            return (
                <View>
                    <Text style={styles.normalText}>Nie ma weryfikacji</Text>
                </View>
            );
        }
    }

    return (
        <View style={styles.centeredContainer}>
            <Text style={styles.title}>Aby rozpocząć sędziowanie musisz połączyć się z serwisem</Text>
            <SafeAreaView>
                <Text style={styles.normalText}>Podaj PIN dostępu do rundy</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={onChangePin}
                    blurOnSubmit={true}
                    value={pin}
                    keyboardType={"default"}
                    />
            </SafeAreaView>
            <Button title={"Połącz"} onPress={() => fetchStarts()}/>
            <View style={styles.centeredContainer}>
                {confirmation(verifyData())}
            </View>
        </View>
    );
}
