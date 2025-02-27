package service;

import java.util.ArrayList;
import java.util.List;

import com.model.Contact;
import com.model.Group;
import com.model.Message;
import com.model.User;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatService {
	private Connection connection;

	public ChatService() throws SQLException, ClassNotFoundException {
		connection = DatabaseConnection.getConnection();
	}

	// User Authentication
	public User authenticateUser(String username, String password) throws SQLException {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return new User(resultSet.getInt("user_id"), resultSet.getString("username"),
					resultSet.getString("password"));
		}
		return null; // Return null if no user is found
	}

	// Send a private message
	public void sendPrivateMessage(int senderId, int receiverId, String messageText) throws SQLException {
		String query = "INSERT INTO messages (sender_id, receiver_id, message_text) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, senderId);
		statement.setInt(2, receiverId);
		statement.setString(3, messageText);
		statement.executeUpdate();
	}

	// Send a group message
	public void sendGroupMessage(int senderId, int groupId, String messageText) throws SQLException {
		String query = "INSERT INTO messages (sender_id, group_id, message_text) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, senderId);
		statement.setInt(2, groupId);
		statement.setString(3, messageText);
		statement.executeUpdate();
	}

	// Retrieve private messages for a user
	public List<Message> getPrivateMessages(int userId) throws SQLException {
		List<Message> messages = new ArrayList<>();
		String query = "SELECT * FROM messages WHERE receiver_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			messages.add(new Message(resultSet.getInt("message_id"), resultSet.getInt("sender_id"),
					resultSet.getInt("receiver_id"), resultSet.getInt("group_id"), resultSet.getString("message_text"),
					resultSet.getTimestamp("timestamp")));
		}
		return messages;
	}

	// Retrieve group messages for a user
	public List<Message> getGroupMessages(int userId) throws SQLException {
		List<Message> messages = new ArrayList<>();
		String query = "SELECT m.* FROM messages m " + "JOIN group_members gm ON m.group_id = gm.group_id "
				+ "WHERE gm.user_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			messages.add(new Message(resultSet.getInt("message_id"), resultSet.getInt("sender_id"),
					resultSet.getInt("receiver_id"), resultSet.getInt("group_id"), resultSet.getString("message_text"),
					resultSet.getTimestamp("timestamp")));
		}
		return messages;
	}

	// Add a contact
	public void addContact(int userId, String contactName, String phoneNumber) throws SQLException {
		String query = "INSERT INTO contacts (user_id, contact_name, phone_number) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		statement.setString(2, contactName);
		statement.setString(3, phoneNumber);
		statement.executeUpdate();
	}

	// Retrieve contacts for a user
	public List<Contact> getContacts(int userId) throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		String query = "SELECT * FROM contacts WHERE user_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			contacts.add(new Contact(resultSet.getInt("contact_id"), resultSet.getInt("user_id"),
					resultSet.getString("contact_name"), resultSet.getString("phone_number")));
		}
		return contacts;
	}

	// Create a group
	public void createGroup(String groupName) throws SQLException {
		String query = "INSERT INTO `groups` (group_name) VALUES (?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, groupName);
		statement.executeUpdate();
	}

	// Add a user to a group
	public void addUserToGroup(int groupId, int userId) throws SQLException {
		String query = "INSERT INTO group_members (group_id, user_id) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, groupId);
		statement.setInt(2, userId);
		statement.executeUpdate();
	}

	// Retrieve groups for a user
	public List<Group> getGroups(int userId) throws SQLException {
		List<Group> groups = new ArrayList<>();
		String query = "SELECT g.* FROM `groups` g " + "JOIN group_members gm ON g.group_id = gm.group_id "
				+ "WHERE gm.user_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			groups.add(new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")));
		}
		return groups;
	}
}