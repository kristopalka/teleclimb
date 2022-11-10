import {View, TouchableOpacity, Text, StyleSheet, SafeAreaView} from "react-native";
import BouncyCheckbox from "react-native-bouncy-checkbox"
import {useState, useEffect} from "react";
import axios from "axios";
import hash from "react-native-web/dist/vendor/hash";
import {Stopwatch} from "react-native-stopwatch-timer";

export function LeadStartPanel ({ip, participant, navigator, startId, hash}) {

    const localStyles = StyleSheet.create({
        container: {
            margin: 15,
            padding: 10,
            alignItems: "center",
            borderBottomWidth: 1,
            borderBottomColor: "black"
        },
        subtitle: {
            fontSize: 20,
            textAlign: "center"
        },
        title: {
            fontWeight: "bold",
            fontSize: 60,
            textAlign: "center"
        },
        club: {
            fontSize: 16,
            textAlign: "center",
            color: "grey"
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
            borderWidth: 2,
            paddingLeft: 10,
            paddingRight: 10,
            paddingTop: 2,
            paddingBottom: 2,
            borderColor: "black",
        },
        navigationButton: {
            backgroundColor: "black",
            padding: 10
        },
        smallContainer: {
            margin: 30
        },
        start: {
            backgroundColor: "#081e7c",
            color: "white",
            padding: 10
        },
        stop: {
            backgroundColor: "#000",
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
        normalText: {
            fontWeight: "bold",
            fontSize: 16,
            textAlign: "center",
            color: "white"
        },
        flexContainer: {
            flexDirection: "row",
            justifyContent: "space-between",
            marginTop: 10,
            padding: 15
        }
    });

    const options = {
        text: {
            fontSize: 30,
            fontWeight: "bold",
            color: '#000',
            margin: 7,
        }
    };

    useEffect(() => clearData(), [participant]);

    function clearData() {
        setNumberOfTries(1);
        setPlus(false);
        captureTime = "00:00:00.000";
        setIsReset(true);
    }

    let captureTime = "00:00:00.000";
    let ipAddressAndPort = "http://" + ip + ":8080";
    const [isRunning, setIsRunning] = useState(false);
    const [isReset, setIsReset] = useState(false);

    async function putScore() {
        let body = {
            value: numberOfTries,
            plus: plus,
            time: getFormattedTime()
        }
        await axios.put(ipAddressAndPort+"/mobile/update-start-score/"+startId +"/"+hash, JSON.stringify(body), {
            headers: {
                'Content-Type': 'text/plain'
            }
        })
            .then(response => console.log("Putting score gives: "  + response.status))
            .catch(error => console.log(error));
    }

    const [numberOfTries, setNumberOfTries] = useState(1);
    const [plus, setPlus] = useState(false);

    let previous = "< Do poprzedniego";
    let next = "Do następnego >";

    function handleCheckBoxChange() {
        setPlus(!plus);
    }

    function incrementNumberOfTries() {
        setNumberOfTries(numberOfTries + 1);
    }

    function decrementNumberOfTries() {
        if(numberOfTries > 1) setNumberOfTries(numberOfTries - 1);
    }

    function navigateToNext() {
        putScore();
        navigator(1);
    }

    function navigateToPrevious() {
        putScore();
        navigator(-1);
    }

    function handleStopwatch() {
        setIsRunning(!isRunning);
        setIsReset(false);
    }

    function clearStopwatch() {
        setIsRunning(false);
        setIsReset(true);
    }

    function getTime(time) {
        captureTime = time;
    }

    function getFormattedTime() {
        return captureTime.substr(0,8) + "." + captureTime.substr(9, captureTime.length);
    }

    return(
        <View>
            <SafeAreaView style={localStyles.container}>
                <Text style={localStyles.title}>{participant.startNumber}</Text>
                <Text style={localStyles.subtitle}>{participant.name + " " + participant.lastName}</Text>
                <Text style={localStyles.club}>Klub sportowy {participant.clubName}</Text>
            </SafeAreaView>
            <View style={localStyles.container}>
                <View>
                    <Text style={localStyles.subtitle}>
                        Punkty
                    </Text>
                </View>
                <View style={localStyles.horizontalFlex}>
                    <TouchableOpacity
                        style={localStyles.button}
                        onPress={() => decrementNumberOfTries()}>
                        <Text style={localStyles.bigText}>-</Text>
                    </TouchableOpacity>
                    <Text style={localStyles.superText}>
                        {numberOfTries}
                    </Text>
                    <TouchableOpacity
                        style={localStyles.button}
                        onPress={() => incrementNumberOfTries()}>
                        <Text style={localStyles.bigText}>+</Text>
                    </TouchableOpacity>
                </View>
                <View style={localStyles.smallContainer}>
                    <BouncyCheckbox
                        size={40}
                        fillColor="black"
                        unfillColor="#FFFFFF"
                        text="Plus"
                        onPress={() => handleCheckBoxChange()}
                        disableBuiltInState={true}
                        isChecked={plus}
                    />
                </View>
                <View style={localStyles.smallContainer}>
                    <Text style={localStyles.subtitle}>Czas:</Text>
                    <Stopwatch
                        msecs
                        start={isRunning}
                        reset={isReset}
                        options={options}
                        startTime={0}
                        getTime={getTime}
                    />
                    <View style={localStyles.horizontalFlexSpace}>
                        <TouchableOpacity style={isRunning ? localStyles.stop : localStyles.start} onPress={() => handleStopwatch()}>
                            <Text style={localStyles.whiteText}>{isRunning ? "Stop" : "Start"}</Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={localStyles.clear} onPress={() => clearStopwatch()}>
                            <Text style={localStyles.whiteText}>Wyczyść</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
            <View style={localStyles.flexContainer}>
                <TouchableOpacity style={localStyles.navigationButton} onPress={() => navigateToPrevious()}>
                    <Text style={localStyles.normalText}>{previous}</Text>
                </TouchableOpacity>
                <TouchableOpacity style={localStyles.navigationButton} onPress={() => navigateToNext()}>
                    <Text style={localStyles.normalText}>{next}</Text>
                </TouchableOpacity>
            </View>
        </View>
    );

}