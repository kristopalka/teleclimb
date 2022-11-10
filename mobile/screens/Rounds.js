import {View, Text, TouchableOpacity, FlatList, StyleSheet, SafeAreaView} from "react-native";
import axios from "axios";
import {ipAddressAndPort} from "../util/constants";
import {useEffect, useState} from "react";

export function Rounds ({route, navigation}) {

    const {competitionId, routeId} = route.params;

    const data =
        [
            {
                id: 1,
                sequenceNumber: 1,
                numberOfRoutes: 6,
                state: "NOT_STARTED",
                competitionId: 1
            },
            {
                id: 2,
                sequenceNumber: 3,
                numberOfRoutes: 4,
                state: "FINISHED",
                competitionId: 2
            },
            {
                id: 3,
                sequenceNumber: 2,
                numberOfRoutes: 8,
                state: "IN_PROGRESS",
                competitionId: 2
            },
            {
                id: 4,
                sequenceNumber: 4,
                numberOfRoutes: 6,
                state: "FINISHED",
                competitionId: 1
            },
        ];

    const styles = StyleSheet.create({
        container: {
            flex: 1,
            marginTop: 10
        },
        item: {
            padding: 10,
            marginVertical: 6,
            marginHorizontal: 10
        },
        title: {
            fontSize: 15,
            color: "black"
        }
    });

    const [rounds, setRounds] = useState([]);

    useEffect(() => {
        fetchAllRoundsOfCompetition(competitionId)
            .then(r => r);
    }, []);

    async function fetchAllRoundsOfCompetition(competitionId) {
        await axios.get(ipAddressAndPort+"/round/all/by/"+competitionId)
            .then(response => setRounds(response.data))
            .catch(error => console.log(error));
    }

    const Item = ({item, onPress, backgroundColor, textColor}) => (
        <TouchableOpacity onPress={onPress} style={[styles.item, backgroundColor]}>
            <Text style={[styles.title, textColor]}>
                {item.id + ". liczba dróg: " + item.numberOfRoutes + " stan: " + item.state}
            </Text>
        </TouchableOpacity>
    );

    function openStarts(roundId, competitionId) {
        navigation.navigate("Starty zawodników", {roundId: roundId, competitionId: competitionId, routeId: routeId});
    }

    const renderItem =({item})=> {

        const color = "black";
        const backgroundColor = "#aeb0b7";

        return(
            <Item
                item={item}
                onPress={() => openStarts(item.id, competitionId)}
                backgroundColor={{backgroundColor}}
                textColor={{color}}
            />
        );
    }

    return (
        <SafeAreaView style={styles.container}>
            <FlatList
                data={rounds}
                renderItem={renderItem}
                keyExtractor={(item) => item.id}
            />
        </SafeAreaView>
    )
}