import sys

sys.path.append('../gen-thrift')

from nextbin.hello import HelloService

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

import traceback

if __name__ == '__main__':
    # Make socket
    transport = TSocket.TSocket('localhost', 12345)
    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)
    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    # Create a client to use the protocol encoder
    client = HelloService.Client(protocol)
    # Connect!
    transport.open()
    try:
        print(client.hello())
        users = client.getUsers(pageNo=1, pageSize=10)
        for user in users:
            print('id: %s' % user.id)
            print('name: %s' % user.name)
        print(users)
    except Thrift.TException as tx:
        traceback.print_exc()
    finally:
        transport.close()
