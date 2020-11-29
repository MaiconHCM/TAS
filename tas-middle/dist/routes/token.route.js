"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
class TokenRoute {
    constructor() {
        this.router = express_1.Router();
        //Inicia as rotas
        this.init();
    }
    init() {
        this.router.post('/', (req, res) => {
            if (req.body.appkey === process.env.APPKEY) {
                const payload = { appkey: process.env.APPKEY };
                res.json({
                    token: jsonwebtoken_1.default.sign(payload, process.env.SECRET, {
                        expiresIn: '24h'
                    })
                });
            }
            else {
                res.status(401).json({ message: 'Invalid Credential' });
            }
        });
    }
}
exports.default = new TokenRoute().router;
//# sourceMappingURL=token.route.js.map