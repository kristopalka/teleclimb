import {View, Text, TouchableOpacity, StyleSheet, TextInput, SafeAreaView} from "react-native";
import BouncyCheckbox from "react-native-bouncy-checkbox";
import {useState, useEffect} from "react";
import {ipAddressAndPort} from "../util/constants";
import axios from "axios";

export function BoulderingStartPanel({participant, startId, hash, navigator}) {

    const styles = StyleSheet.create({
        container: {
            margin: 15,
            padding: 10,
            alignItems: "center",
            borderBottomWidth: 1,
            borderBottomColor: "black"
        },
        subtitle: {
            fontSize: 20,
            margin: 5,
            textAlign: "center"
        },
        bigText: {
            fontWeight: "bold",
            fontSize: 45,
            padding: 10
        },
        superText: {
            fontSize: 55,
            fontWeight: "bold",
            padding: 10,
            marginLeft: 10,
            marginRight: 10
        },
        horizontalFlex: {
            width: 200,
            flexDirection: "row",
            justifyContent: "center"
        },
        button: {
            backgroundColor: "#c1bab4",
            marginLeft: 10,
            marginRight: 10,
            borderWidth: 2,
            paddingLeft: 15,
            paddingRight: 15,
            paddingTop: 2,
            paddingBottom: 2,
            borderColor: "black",
        },
        smallContainerFlex: {
            margin: 30,
            flexDirection: "row",
            justifyContent: "center"
        },
        smallContainer: {
            margin: 20,
            alignItems: "center"
        },
        start: {
            backgroundColor: "#081e7c",
            color: "white",
            padding: 10
        },
        clear: {
            backgroundColor: "red",
            color: "white",
            padding: 10
        },
        horizontalFlexSpace: {
            flexDirection: "row",
            justifyContent: "space-between",
        },
        whiteText: {
            fontWeight: "bold",
            fontSize: 20,
            color: "white",
            textAlign: "center"
        },
        smallText: {
            fontWeight: "bold",
            fontSize: 15
        },
        input: {
            height: 40,
            width: 40,
            margin: 12,
            borderBottomWidth: 1,
            paddingLeft: 7,
            fontSize: 20
        },
        biggerText: {
            padding: 8,
            fontSize: 35
        },
        title: {
            fontSize: 60,
            fontWeight: "bold",
            textAlign: "center"
        },
        navigateButton: {
            backgroundColor: "black",
            padding: 10
        },
        club: {
            fontSize: 16,
            textAlign: "center",
            color: "grey"
        },
        flexContainer: {
            flexDirection: "row",
            justifyContent: "space-between",
            marginTop: 10,
            padding: 15
        },
        normalText: {
            fontWeight: "bold",
            fontSize: 16,
            textAlign: "center",
            color: "white"
        }
    });

    useEffect(() => clearFields(), [participant]);

    function clearFields() {
        setNumberOfTries(1);
        setTriesToBonus(1);
        setTop(false);
        setAtWhichTryIsTop("1");
        setBonus(false);
    }

    function validateStartData() {
        if(bonus) {
            if(triesToBonus === "" || parseInt(triesToBonus < 1) || parseInt(triesToBonus) > numberOfTries) {
                return false;
            }
        }
        return true;
    }

    function getStartData() {
        if(validateStartData())
            return {
                bonus: bonus,
                tries: numberOfTries,
                triesToBonus: triesToBonus,
                top: top
            }
    }

    async function putStartData() {
        await axios
            .put(ipAddressAndPort+"/mobile/update-start-score/"+startId+"/"+hash, JSON.stringify(getStartData()), {
                headers: {
                    'Content-Type': 'text/plain'
                }
            })
            .then(response => response)
            .catch(error => console.log(error));
    }

    function incrementTries() {
        setNumberOfTries(numberOfTries + 1);
        if(!top) setAtWhichTryIsTop(numberOfTries + 1);
    }

    function decrementDries() {
        if(numberOfTries > 1) setNumberOfTries(numberOfTries - 1);
        if(!top) setAtWhichTryIsTop(numberOfTries - 1);
    }

    function parseBouns() {
        if(bonus === false) return "NIE";
        else return "TAK";
    }

    function updateBonus() {
        setBonus(!bonus);
    }

    function updateTop() {
        setTop(!top);
    }

    const [numberOfTries, setNumberOfTries] = useState(1);
    const [atWhichTryIsTop, setAtWhichTryIsTop] = useState(1);
    const [bonus, setBonus] = useState(false);
    const [top, setTop] = useState(false);
    const [triesToBonus, setTriesToBonus] = useState("");

    let previous = "< Do poprzedniego";
    let next = "Do następnego >";

    function navigateToNext() {
        putStartData();
        navigator(1);
    }

    function navigateToPrevious() {
        putStartData();
        navigator(-1);
    }

    return (
        <View>
            <View>
                <SafeAreaView style={styles.container}>
                    <Text style={styles.title}>{participant.startNumber}</Text>
                    <Text style={styles.subtitle}>{participant.name + " " + participant.lastName}</Text>
                    <Text style={styles.club}>Klub sporotowy {participant.clubName}</Text>
                </SafeAreaView>
            </View>
            <View style={styles.container}>
                <Text style={styles.subtitle}>
                    Próby
                </Text>
                <View style={styles.horizontalFlex}>
                    <TouchableOpacity style={styles.button} onPress={() => decrementDries()}>
                        <Text style={styles.bigText}>-</Text>
                    </TouchableOpacity>
                    <Text style={styles.superText}>
                        {numberOfTries}
                    </Text>
                    <TouchableOpacity style={styles.button} onPress={() => incrementTries()}>
                        <Text style={styles.bigText}>+</Text>
                    </TouchableOpacity>
                </View>
                <View style={styles.smallContainerFlex}>
                    <View style={styles.smallContainer}>
                        <BouncyCheckbox
                            size={40}
                            fillColor={"red"}
                            style={{marginLeft: 15}}
                            onPress={() => updateBonus()}
                            isChecked={bonus}
                            disableBuiltInState={true}
                        />
                        <Text style={styles.subtitle}>Bonus: {parseBouns(bonus)}</Text>
                        <Text style={styles.smallText}>Przy próbie:</Text>
                        <TextInput
                            keyboardType={"numeric"}
                            style={styles.input}
                            value={triesToBonus}
                            onChangeText={setTriesToBonus}
                        />
                    </View>
                    <View style={styles.smallContainer}>
                        <BouncyCheckbox
                            size={40}
                            style={{marginLeft: 15}}
                            onPress={() => updateTop()}
                            isChecked={top}
                            disableBuiltInState={true}
                        />
                        <Text style={styles.subtitle}>Top</Text>
                        <Text style={styles.smallText}>Przy próbie: {atWhichTryIsTop}</Text>
                    </View>
                </View>
            </View>
            <View style={styles.flexContainer}>
                <TouchableOpacity style={styles.navigateButton} onPress={() => navigateToPrevious()}>
                    <Text style={styles.normalText}>{previous}</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.navigateButton} onPress={() => navigateToNext()}>
                    <Text style={styles.normalText}>{next}</Text>
                </TouchableOpacity>
            </View>
        </View>
    );

}