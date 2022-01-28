class App extends React.Component {
    constructor() {
        super()
        this.state = {
            messages: [],
        	ctx: {usr: 'CryptoBot'}
        }
        this.sendMessage = this.sendMessage.bind(this)
        
    } 
    
    componentDidMount() {
    	this.sendMessage("");
    }

    sendMessage(text) {
    	if(text != "") 
	    	this.setState({
	            messages: [...this.state.messages, {"usr": "Usuario", "text": text}]
	        })
        var ctxStr = JSON.stringify(this.state.ctx);
    	//fetch(`http://localhost:9080/WatsonAssistantChat/chatbot/chatservice/?conversationMsg=${encodeURIComponent(text)}&conversationCtx=${encodeURIComponent(ctxStr)}`)
    	fetch(`https://pivotsolutionscryptov2.mybluemix.net/chatbot/chatservice/?conversationMsg=${encodeURIComponent(text)}&conversationCtx=${encodeURIComponent(ctxStr)}`)
    	.then((response) => {
          return response.json()
        })
        .then((myJsonResponse) => {
        	this.setState({
	            messages: [...this.state.messages, {"usr": myJsonResponse.context.usr, "text": myJsonResponse.response}],
        		ctx: myJsonResponse.context
	        })
        })
    }
    
    render() {
        return (
            <div className="app">
              <Title />
              <MessageList
                  messages={this.state.messages} />
              <SendMessageForm
                  sendMessage={this.sendMessage} />
            </div>
        );
    }
}

class MessageList extends React.Component {
    render() {
        return (
            <ul className="message-list">
                {this.props.messages.map((message, index) => {
                    return (
                      <li  key={index} className="message">
                      	<div>{message.usr}</div>  
                      	<div>{message.text}</div>
                      </li>
                    )
                })}
            </ul>
        )
    }
}

class SendMessageForm extends React.Component {
    constructor() {
        super()
        this.state = {
            message: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }
    
    handleChange(e) {
        this.setState({
            message: e.target.value
        })
    }
    
    handleSubmit(e) {
        e.preventDefault()
        this.props.sendMessage(this.state.message)
        this.setState({
            message: ''
        })
    }
    
    render() {
        return (
            <form
                onSubmit={this.handleSubmit}
                className="send-message-form">
                <input
                    onChange={this.handleChange}
                    value={this.state.message}
                    placeholder="Escriba su mensaje y presione INTRO"
                    type="text" />
            </form>
        )
    }
}

function Title() {
  return <p className="title">Pivot Solutions Crypto</p>
}

ReactDOM.render(<App />, document.getElementById('root'));