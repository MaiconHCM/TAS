"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
const axios_1 = __importDefault(require("axios"));
class APIRoute {
    constructor() {
        this.router = express_1.Router();
        //Inicia as rotas
        this.init();
    }
    validateToken(req) {
        //Verificar se a requisição tem um Authorization
        if (req.headers.authorization && req.headers.authorization.split(' ')[0] == 'Bearer') {
            let token = req.headers.authorization.split(' ')[1];
            try {
                jsonwebtoken_1.default.verify(token, process.env.SECRET);
            }
            catch (error) {
                throw ({ message: error.name == 'TokenExpiredError' ? 'Token expired' : 'Token invalid' });
            }
        }
        else {
            throw ({ message: 'Request unauthorized' });
        }
    }
    init() {
        this.router.route('/:recurso')
            .all((req, res, next) => {
            try {
                this.validateToken(req);
                next();
            }
            catch (error) {
                res.status(401).json(error);
            }
        })
            .get((req, res) => {
            axios_1.default.get(process.env.SERVER + '/' + req.params.recurso)
                .then(result => {
                res.status(result.status).json(result.data);
            })
                .catch(error => {
                console.log(error);
                res.json(error);
            });
        })
            .post((req, res) => {
            axios_1.default.post(process.env.SERVER + '/' + req.params.recurso, req.body)
                .then(result => {
                res.status(result.status).json(result.data);
            })
                .catch(error => {
                console.log(error);
                res.json(error);
            });
        });
        this.router.route('/:recurso/:id')
            .all((req, res, next) => {
            try {
                this.validateToken(req);
                next();
            }
            catch (error) {
                res.status(401).json(error);
            }
        })
            .get((req, res) => {
            axios_1.default.get(process.env.SERVER + '/' + req.params.recurso + '/' + req.params.id)
                .then(result => {
                res.status(result.status).json(result.data);
            })
                .catch(error => {
                res.status(error.response.status)
                    .json(error.response.data);
            });
        })
            .put((req, res) => {
            axios_1.default.put(process.env.SERVER + '/' + req.params.recurso + '/' + req.params.id, req.body)
                .then(result => {
                res.status(result.status).json(result.data);
            })
                .catch(error => {
                res.status(error.response.status)
                    .json(error.response.data);
            });
        })
            .delete((req, res) => {
            axios_1.default.delete(process.env.SERVER + '/' + req.params.recurso + '/' + req.params.id)
                .then(result => {
                res.status(result.status).json(result.data);
            })
                .catch(error => {
                res.status(error.response.status)
                    .json(error.response.data);
            });
        });
    }
}
exports.default = new APIRoute().router;
//# sourceMappingURL=api.route.js.map