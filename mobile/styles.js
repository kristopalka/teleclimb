import {StyleSheet} from "react-native";

export const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        justifyContent: "space-between",
        alignItems: "center"
    },
    ctn: {
        padding: 10,
        margin: 10,
    },
    logo: {
        height: 200,
        width: 200,
        marginTop: 100
    },
    title: {
        fontSize: 40,
        fontWeight: "bold",
        margin: 20,
        textAlign: "center"
    },
    subtitle: {
        fontSize: 15,
        margin: 15,
        textAlign: "center"
    },
    boldSubtitle: {
        fontSize: 15,
        margin: 10,
        textAlign: "center",
        fontWeight: "bold"
    },
    menuItem: {
        margin: 10,
        padding: 10,
        alignItems: "center"
    },
    menuText: {
        color: "grey",
        fontWeight: "bold",
        textAlign: "center",
        fontSize: 17
    },
    menu: {
        flexDirection: "row",
        justifyContent: "center",
        backgroundColor: "#e6e6e6",
        width: "100%"
    },
    icon: {
        width: 20,
        height: 20,
        padding: 5,
    },
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
        padding: 10
    },
    normalText: {
        textAlign: "center",
        marginTop: 10,
        fontSize: 18
    },
    centeredContainer: {
        flex: 1,
        justifyContent: "center",
        padding: 30
    },
    mediumTitle: {
        fontSize: 30,
        fontWeight: "bold",
        margin: 20,
        textAlign: "center",
        marginTop: -90
    },
    confirmText: {
        textAlign: "center",
        padding: 10,
        fontSize: 15,
        fontWeight: "bold"
    },
    whiteSubtitle: {
        fontSize: 15,
        color: "white"
    },
    horizontalFlex: {
        flexDirection: "row",
        justifyContent: "center",
        padding: 15
    },
    bigIcon: {
        height: 30,
        width: 30,
        margin: 5,
        marginTop: -5
    },
    upContainer: {
        marginTop: -30
    }
});